/*
 * ** github: https://github.com/bitstwinkle ***
 * ** gitee: https://gitee.com/bitstwinkle ***
 * ** 比特闪耀-技术让世界更美丽 ***
 * Copyright © 2020-2030 Hangzhou BitsTwinkle Information Technology Co.,Ltd. All rights reserved.
 */

package tech.bitstwinkle.jelly.platform.access.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import tech.bitstwinkle.jelly.commons.errors.JellyException;
import tech.bitstwinkle.jelly.commons.errors.enums.SystemErrorEnum;
import tech.bitstwinkle.jelly.commons.ids.JellyIds;
import tech.bitstwinkle.jelly.commons.proto.Meta;
import tech.bitstwinkle.jelly.commons.proto.Request;
import tech.bitstwinkle.jelly.commons.proto.Response;

/**
 * @author suuyoo on 2020-03-01
 */
@Aspect
@Component
public class AccessEntranceAspect {

    private static final Logger SERVER_DIGEST = LoggerFactory.getLogger("SERVER-DIGEST");

    @Pointcut("@annotation(tech.bitstwinkle.jelly.platform.access.annotation.AccessEntrance)")
    public void accessEntrance() {

    }

    @Around("accessEntrance()")
    public Object aroundAccessEntrance(ProceedingJoinPoint proceedingJoinPoint) {
        String clazz = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String method = proceedingJoinPoint.getSignature().getName();

        Meta requestMeta = null;
        Object respResult;

        try {

            /**
             * 前置处理
             */
            requestMeta = doBefore(proceedingJoinPoint, method);

            /**
             * 调用目标方法
             */
            long start = System.currentTimeMillis();
            respResult = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();
            SERVER_DIGEST.info("I - {}.{} - Execution time:{} ms",
                proceedingJoinPoint.getTarget().getClass().getSimpleName(),
                method,
                (end - start));

            /**
             * 后置处理
             */
            doAfter(clazz, method, respResult);

        } catch (JellyException ex) {
            SERVER_DIGEST.info("E - {}.{} : {}@{}", clazz, method, ex.getError().getCode(),
                ex.getError().getMessage());
            respResult = new Response<>(ex.getError());
        } catch (Throwable thr) {
            SERVER_DIGEST.info("E - {}.{} : {}", clazz, method, thr);
            respResult = new Response<>(SystemErrorEnum.SYSTEM_ERROR.getError());
        }

        if (respResult != null && respResult instanceof Response) {
            Meta respMeta = new Meta();
            if (requestMeta != null) {
                respMeta.setRequestId(requestMeta.getRequestId());
                respMeta.setTraceId(requestMeta.getTraceId());
            } else {
                respMeta.setRequestId("no-src-requestId");
                respMeta.setTraceId(JellyIds.uniqueId());
            }
            respMeta.setDt(System.currentTimeMillis());
            ((Response) respResult).setMeta(respMeta);
        }

        /**
         * 返回结果
         */
        return respResult;
    }


    /**
     * 后置处理
     *
     * @param clzName
     * @param method
     * @param result
     */
    private void doAfter(String clzName, String method, Object result) {
        if (result == null) {
            result = "void";
        }

        SERVER_DIGEST.info("O - {}.{} - Response: {}", clzName, method, result);

        MDC.remove("traceId");
    }

    /**
     * 前置处理
     *
     * @param joinPoint
     * @param method
     */
    private Meta doBefore(JoinPoint joinPoint, String method) {
        Object[] args = joinPoint.getArgs();

        Request request = null;
        if (args != null) {
            if (args[0] != null && (args[0] instanceof Request)) {
                request = (Request) args[0];
            }
        }

        if (request == null) {
            SERVER_DIGEST.info("I - {}.{} : {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                method,
                joinPoint.getArgs());
            return null;
        }

        Meta meta = request.getMeta();

        if (meta != null) {
            MDC.put("traceId", meta.getTraceId());
        }

        SERVER_DIGEST.info("I - {}.{} : [{}]{}",
            joinPoint.getTarget().getClass().getSimpleName(),
            method,
            meta,
            joinPoint.getArgs());
        return meta;
    }
}
