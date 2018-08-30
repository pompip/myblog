package cn.pompip.myblog.interceptor

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionInterceptor {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(value = [Exception::class])
    fun defaultErrorHandler(request: HttpServletRequest, e: Exception): ModelAndView {
        val mav = ModelAndView()
        mav.addObject("errorCode", 500)
        mav.addObject("errorURL", request.requestURL)
        val stackTrace = StringBuilder(e.message)
        stackTrace.append("\n\n")
        for (element in e.stackTrace) {
            stackTrace.append(element).append("\n")
        }
        mav.addObject("errorStack", stackTrace.toString())
        mav.viewName = "error"
        val requestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        logger.info("url={}", requestAttributes.request.requestURI)
        return mav
    }
}