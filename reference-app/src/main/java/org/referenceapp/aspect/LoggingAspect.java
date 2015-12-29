package org.referenceapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.referenceapp.annotation.LogExecutionMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * LoggingAspect will have methods to log messages for debugging and capturing metrics.
 *
 * @author kirankandala
 */
@Aspect
public class LoggingAspect {

    private static final Logger perfLogger = LoggerFactory.getLogger("perf");
    private static final Logger appLogger = LoggerFactory.getLogger("app");

    /**
     * Profile method will capture the execution times for the methods annotated with LogExecutionMetrics.
     *
     * @param joinPoint the joinPoint
     * @return the object
     */
    @Around(value = "@annotation(annotation)")
    public Object profile(ProceedingJoinPoint joinPoint, LogExecutionMetrics annotation) throws Throwable {

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object returnValue = null;

        appLogger.info(buildMethodName(joinPoint) + "->Start");
        try {
            returnValue = joinPoint.proceed();
        } finally {
            stopWatch.stop();
            appLogger.info("Return Value is " + returnValue);
            appLogger.info(buildMethodName(joinPoint) + "->End");
            logMetrics(joinPoint, stopWatch.getTotalTimeMillis());
        }

      return returnValue;
    }

    private StringBuffer logMetrics(ProceedingJoinPoint joinPoint, long timeInMillis) {

        final StringBuffer message = buildMethodName(joinPoint)
                .append(" execution time: ")
                .append(timeInMillis)
                .append(" ms");

        perfLogger.info(message.toString());

        return message;
    }

    private StringBuffer buildMethodName(ProceedingJoinPoint joinPoint) {
        final StringBuffer message = new StringBuffer()
                .append(joinPoint.getTarget().getClass().getSimpleName())
                .append(".")
                .append(joinPoint.getSignature().getName())
                .append("(");

        final Object[] args = joinPoint.getArgs();
        for (final Object arg : args) {
            message.append(arg).append(",");
        }

        if (args.length > 0) {
            //remove the comma after the last method parameter.
            message.deleteCharAt(message.length() - 1);
        }

        message.append(")");

        return message;
    }
}