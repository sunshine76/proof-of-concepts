package org.referenceapp.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PerformanceMetricAspect.
 */
@Aspect
public class PerformanceMetricAspect {

    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(PerformanceMetricAspect.class);

    /**
     * Profile.
     * 
     * @param joinPoint the joinPoint
     * @return the object
     * @throws Throwable the throwable
     */
    @Around(value = "@annotation(annotation)")
    public Object profile(ProceedingJoinPoint joinPoint, LogExecutionMetrics annotation) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object retVal = null;
        long timeTaken = 0;
        try {
            retVal = joinPoint.proceed();
            timeTaken = System.currentTimeMillis()-startTime;
        } finally {
            LOG.info("Performance************:"+joinPoint.getThis().getClass().getSimpleName()+"."+joinPoint.getSignature().getName()+" took:"+timeTaken+" ms");
        }
        return retVal;
    }
}