package com.my.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * aop->面向切面
 * 生成日志访问-ip-url-控制器方法-对象
 * @auther 周经明
 * @date 2020/3/16 13:39
 */
@Aspect
@Component
public class LogAspect {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

//    设置拦路径 本路径为web下的所有控制器中的所有方法（不限参数）
    @Pointcut("execution(* com.my.blog.web.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
//        拿到HttpServletRequest对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

//        拿到所需值
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object args[] = joinPoint.getArgs();


        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        logger.info("Request: {}",requestLog);
    }

    @After("log()")
    public void doAfter(){
        logger.info("after-----------------");
    }


    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("afterReturn-----------------Result: {}",result);
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String ClassMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            ClassMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", ClassMethod='" + ClassMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
