/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.platform.tx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import tech.bitstwinkle.jelly.commons.errors.JellyException;
import tech.bitstwinkle.jelly.commons.errors.enums.SystemErrorEnum;

/**
 * @author suuyoo.wg on 2020/2/29
 */
@Component
public class JellyTxTemplate {

  private static final Logger LOGGER = LoggerFactory.getLogger(JellyTxTemplate.class);

  @Autowired
  private PlatformTransactionManager platformTransactionManager;

  public <T> T execute(TransactionCallback<T> callback) {

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("tx execute start");
    }

    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    def.setTimeout(30);

    TransactionStatus status;

    try {
      status = platformTransactionManager.getTransaction(def);
    } catch (JellyException e) {
      LOGGER.warn("getTransaction throw Exception", e);
      throw new JellyException(e.getError());
    } catch (Exception e) {
      LOGGER.error("getTransaction throw Exception", e);
      throw new JellyException(SystemErrorEnum.SYSTEM_ERROR.getError());
    }

    T result = null;

    try {
      result = callback.doInTransaction(status);
    } catch (RuntimeException re) {
      LOGGER.error("throw RuntimeException", re);
      rollbackOnException(status, re);
    } catch (Error err) {
      LOGGER.error("throw Error", err);
      rollbackOnException(status, err);
    } catch (Exception e) {
      LOGGER.error("throw Exception", e);
      rollbackOnException(status, e);
    }

    try {
      platformTransactionManager.commit(status);
    } catch (Exception e) {
      LOGGER.error("commit throw Exception", e);
      throw new JellyException(SystemErrorEnum.SYSTEM_ERROR.getError());
    }

    return result;

  }

  /********************** inner methods ******************************/
  private void rollbackOnException(TransactionStatus status, Throwable ex) throws JellyException {
    try {
      platformTransactionManager.rollback(status);
    } catch (JellyException e) {
      LOGGER.warn("getTransaction throw Exception", e);
      throw new JellyException(e.getError());
    } catch (TransactionSystemException tse) {
      LOGGER.error("rollback throw TransactionSystemException", tse);
      tse.initApplicationException(ex);
      throw new JellyException(SystemErrorEnum.SYSTEM_ERROR.getError());
    } catch (RuntimeException re) {
      LOGGER.error("rollback throw Exception", re);
    } catch (Error err) {
      LOGGER.error("rollback throw Error", err);
    }
    if (ex instanceof JellyException) {
      JellyException ex2 = (JellyException) ex;
      LOGGER.warn("getTransaction throw Exception", ex2);
      throw new JellyException(ex2.getError());
    }
    throw new JellyException(SystemErrorEnum.SYSTEM_ERROR.getError());
  }
}
