package cn.pompip.myblog.server

import cn.pompip.myblog.dao.ArticleDao
import cn.pompip.myblog.entity.ArticleEntity
import cn.pompip.myblog.exe.ArticleWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ArticleServer {
    @Autowired
    lateinit var dao: ArticleDao;

    fun getAll(): List<ArticleEntity> = dao.findAll()

    fun getOne(id: Long): ArticleEntity = dao.getOne(id)

    fun saveArticle(content: String) {
        val articleWrapper = ArticleWrapper(content);
        val articleEntity = ArticleEntity()

    }

}