package cn.pompip.myblog.exe

import java.sql.Timestamp

class ArticleWrapper(var con:String){

    val timestamp:Timestamp = Timestamp(System.currentTimeMillis());
    fun getTitle(){

    }

    fun getContent(){

    }
}