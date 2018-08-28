package cn.pompip.myblog.interceptor

import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class LogAspect {

    var logger = LoggerFactory.getLogger(this.javaClass)!!

    @Pointcut("execution(public * cn.pompip.myblog.control..*(..))")
    fun log() {

    }

    @Before("log()")
    fun doBefore() {
        val requestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val request = requestAttributes.request
        logger.info("url={}", request.requestURI)

    }

    @After("log()")
    fun doAfter() {

    }
}