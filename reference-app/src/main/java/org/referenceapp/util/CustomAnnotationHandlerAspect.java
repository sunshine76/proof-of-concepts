package org.referenceapp.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * CustomAnnotationHandlerAspect will have methods to handle custom annotations defined.
 *
 * @author kirankandala
 */
@Aspect
public class CustomAnnotationHandlerAspect {

    private static final Logger LOG = LoggerFactory.getLogger(CustomAnnotationHandlerAspect.class);

    /**
     * Profile method will capture the execution times for the methods annotated with LogExecutionMetrics.
     *
     * @param joinPoint the joinPoint
     * @return the object
     * @throws Throwable the throwable
     */
    @Around(value = "@annotation(annotation)")
    public Object profile(ProceedingJoinPoint joinPoint, LogExecutionMetrics annotation) throws Throwable {

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final Object returnValue = joinPoint.proceed();
        stopWatch.stop();

        logMetrics(joinPoint, stopWatch.getTotalTimeMillis());

        return returnValue;
    }

    private StringBuffer logMetrics(ProceedingJoinPoint joinPoint, long timeInMillis) {
        final StringBuffer message = new StringBuffer();
        message.append(joinPoint.getTarget().getClass().getSimpleName());
        message.append(".");
        message.append(joinPoint.getSignature().getName());
        message.append("(");

        final Object[] args = joinPoint.getArgs();
        for (final Object arg : args) {
            message.append(arg).append(",");
        }

        if (args.length > 0) {
            message.deleteCharAt(message.length() - 1);
        }

        message.append(")");
        message.append(" execution time: ");
        message.append(timeInMillis);
        message.append(" ms");

        LOG.info(message.toString());

        return message;
    }
}