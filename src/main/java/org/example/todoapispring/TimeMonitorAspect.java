package org.example.todoapispring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {

    @Around("@annotation(TimeMonitor)")
    public void logtime(ProceedingJoinPoint joinPoint) {

        long startTime = System.currentTimeMillis();

        try {
            // execute the join point
            joinPoint.proceed();
        }
        catch (Throwable e) {
            System.out.println("Something went wrong during execution");
        }
        finally {

            long endTime = System.currentTimeMillis();
            System.out.println("Time taken for execution: " + (endTime - startTime) + " ms");
        }
    }
}
