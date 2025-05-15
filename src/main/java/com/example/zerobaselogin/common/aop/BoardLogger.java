package com.example.zerobaselogin.common.aop;

import com.example.zerobaselogin.logs.service.LogService;
import com.example.zerobaselogin.user.entity.User;
import com.example.zerobaselogin.user.model.UserLogin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class BoardLogger {

    private final LogService logService;

    @Around("execution(* com.example.zerobaselogin..*.*Controller.detail(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("###############################");
        log.info("###############################");
        log.info("컨트롤러detail 호출 전!!!!!");

        Object result = joinPoint.proceed();

        if (joinPoint.getSignature().getDeclaringTypeName().contains("ApiBoardController")
            && "detail".equals(joinPoint.getSignature().getName())) {

            StringBuilder sb = new StringBuilder();

            sb.append("파라미터: ");
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                sb.append(arg.toString());
            }

            sb.append("\n");
            sb.append("결과: ");
            sb.append(result.toString());

            logService.add(sb.toString());
            log.info(sb.toString());
        }

        log.info("###############################");
        log.info("###############################");
        log.info("컨트롤러detail 호출 후!!!!!");

        return result;
    }
}
