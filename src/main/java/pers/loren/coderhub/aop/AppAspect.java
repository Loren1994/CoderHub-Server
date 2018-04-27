package pers.loren.coderhub.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pers.loren.coderhub.domain.BaseDTO;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

@Aspect
@Component
@Order(2)
public class AppAspect {

    @Around("execution (* pers.loren.coderhub.controller.*Controller.*(..))")
    public Object universalLogback(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Logger logger = LoggerFactory.getLogger(proceedingJoinPoint.getTarget().getClass());
        logger.info(">>>>>>>>>>日志开始<<<<<<<<<<<");
        logger.info(request.getRequestURL().toString());
        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Object target = proceedingJoinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        logger.info("\n调用的类名称为：" + proceedingJoinPoint.getTarget().getClass() + ",\n"
                + "调用的方法名为：" + currentMethod.getName() + ",\n"
                + "方法的参数为：" + parseObjToJson(proceedingJoinPoint.getArgs()) + ",\n");
        logger.info(">>>>>>>>>>日志结束<<<<<<<<<<<");
        return proceedingJoinPoint.proceed();
    }

    private String parseObjToJson(Object[] objs) {
        if (objs != null && objs.length > 0) {
            return Arrays.stream(objs)
                    .filter((s) -> s instanceof HashMap || s instanceof BaseDTO)
                    .map(JSON::toJSONString)
                    .collect(Collectors.joining());
        }
        return "";
    }
}
