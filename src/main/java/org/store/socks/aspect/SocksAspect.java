package org.store.socks.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * The type Socks aspect.
 */
@Slf4j
@Aspect
@Component
public class SocksAspect {

  /**
   * Pointcut to socks repository.
   */
  @Pointcut("this(org.store.socks.repository.implementation.SocksRepositoryImpl)")
  public void socksRepository() {
  }

  /**
   * Pointcut to socks service.
   */
  @Pointcut("this(org.store.socks.service.SocksService)")
  public void socksService() {
  }

  /**
   * Pointcut to socks controller.
   */
  @Pointcut("this(org.store.socks.controller.SocksController)")
  public void socksController() {
  }

  /**
   * Log successful execution.
   *
   * @param joinPoint      the join point
   * @param returningValue the returning value
   */
  @AfterReturning(
      value = "(socksRepository() "
      + "|| socksService() "
      + "|| socksController())",
      returning = "returningValue")
  public void logSuccessfulExecution(JoinPoint joinPoint, Object returningValue) {
    if (returningValue != null) {
      log.info("Successful execution of {} in {} with result {}",
          getMethodName(joinPoint),
          getClassName(joinPoint),
          returningValue);
    } else {
      log.info("Successful execution of {} in {} without returning value",
          getMethodName(joinPoint),
          getClassName(joinPoint));
    }
  }

  /**
   * Log failed execution.
   *
   * @param joinPoint the join point
   * @param exception the exception
   */
  @AfterReturning(
      value = "(socksRepository() "
          + "|| socksService() "
          + "|| socksController())",
      returning = "exception")
  public void logFailedExecution(JoinPoint joinPoint, Exception exception) {
    log.info("Method {} from {} has ended throwing {}",
        getMethodName(joinPoint),
        getClassName(joinPoint),
        exception.getMessage());
  }

  private String getMethodName(JoinPoint joinPoint) {
    return joinPoint.getSignature().getName();
  }

  private String getClassName(JoinPoint joinPoint) {
    return joinPoint.getSignature().getDeclaringType().getName();
  }
}
