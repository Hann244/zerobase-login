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
public class LoginLogger {

    private final LogService logService;

    @Around("execution(* com.example.zerobaselogin..*.*Service*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("###############################");
        log.info("###############################");
        log.info("서비스 호출 전!!!!!");

        Object result = joinPoint.proceed();

        if ("login".equals(joinPoint.getSignature().getName())) {

            StringBuilder sb = new StringBuilder();

            sb.append("\n");
            sb.append("함수명:" + joinPoint.getSignature().getDeclaringTypeName() + ", " + joinPoint.getSignature().getName());
            sb.append("\n");
            sb.append("매개변수: ");

            Object[] args = joinPoint.getArgs();
            if (args != null && args.length > 0) {
                for (Object arg : args) {
                    if (arg instanceof UserLogin) {
                        sb.append(((UserLogin) arg).toString());

                        // result를 저장
                        sb.append("\n");
                        sb.append("리턴값: " + ((User) result).toString());

                    }
                }
            }
            logService.add(sb.toString());
            log.info(sb.toString());
        }

        log.info("###############################");
        log.info("###############################");
        log.info("서비스 호출 후!!!!!");

        return result;
    }
}
