/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.platform.dal;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author suuyoo.wg on 2020/2/29
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class SelfPkEntity implements Serializable {

  private static final long serialVersionUID = -811023918344930364L;

  /**
   * 自增id
   */
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "auto_id", nullable = false, insertable = false, updatable = false)
  protected Long autoId;

  /**
   * 数据创建时间
   */
  @CreatedDate
  @Column(name = "gmt_create", updatable = false)
  protected Date gmtCreate;

  /**
   * 数据更新时间
   */
  @LastModifiedDate
  @Column(name = "gmt_modified", insertable = false)
  protected Date gmtModified;

  public Long getAutoId() {
    return autoId;
  }

  public void setAutoId(Long autoId) {
    this.autoId = autoId;
  }

  public Date getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(Date gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public Date getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(Date gmtModified) {
    this.gmtModified = gmtModified;
  }

}
