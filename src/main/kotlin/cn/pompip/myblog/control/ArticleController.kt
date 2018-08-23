package cn.pompip.myblog.control

import cn.pompip.myblog.entity.ArticleEntity
import cn.pompip.myblog.server.ArticleServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/article")
class ArticleController {

    @Autowired
    lateinit var server: ArticleServer

    @RequestMapping("/getArticleList")
    @ResponseBody
    fun getArticleList(): List<ArticleEntity> {
        return server.getAll()
    }
}