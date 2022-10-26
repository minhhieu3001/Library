package vn.com.ntqsolution.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingHandler {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
    }

    @Pointcut("within(vn.com.ntqsolution..*)" + " || within(vn.com.ntqsolution.controller..*)")
    public void packagePointcut() {
    }

    @AfterThrowing(pointcut = "packagePointcut() && springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }


    @Before(value = "execution(* vn.com.ntqsolution.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Executing {}", joinPoint);
    }

    @Around(value = "execution(* vn.com.ntqsolution.controller.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long timeStart = System.currentTimeMillis();
        try {
            Object obj = joinPoint.proceed();
            long timeExecuting = System.currentTimeMillis() - timeStart;
            log.info("Time executing {}", timeExecuting);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
