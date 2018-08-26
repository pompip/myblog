package cn.pompip.myblog.utils

import org.slf4j.LoggerFactory


fun Any.loge(msg:String){
    LoggerFactory.getLogger(this::class.java).error(msg)
}