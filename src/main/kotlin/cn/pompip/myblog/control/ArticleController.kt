package cn.pompip.myblog.control

import cn.pompip.myblog.entity.ArticleEntity
import cn.pompip.myblog.server.ArticleServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/article")
class ArticleController {

    @Autowired
    lateinit var server: ArticleServer

    @PostMapping("/list")
    fun getArticleList(): List<ArticleEntity> = server.getIndexArticleList()


    @PostMapping("/{id}")
    fun getArticle(@PathVariable id: Long) = server.getOne(id)

    @PostMapping("/save")
    fun saveArticle(content: String) = server.saveArticle(content).id


    @PostMapping("/update")
    fun updateArticle(content: String, id: Long) = server.updateArticle(content, id).id

    @PostMapping("/delete/{id}")
    fun deleteArticle( @PathVariable id: Long) = server.deleteArticle(id)
}