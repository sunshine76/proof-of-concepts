package org.referenceapp.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CustomAnnotationHandlerAspect will have methods to handle custom annotations defined.
 * 
 * @author kirankandala
 */
@Aspect
public class CustomAnnotationHandlerAspect {

    /** The Constant LOG. */
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