package cn.pompip.myblog.server

import cn.pompip.myblog.dao.ArticleDao
import cn.pompip.myblog.entity.ArticleEntity
import cn.pompip.myblog.exe.ArticleWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class ArticleServer {
    @Autowired
    lateinit var dao: ArticleDao;

    fun getIndexArticleList(): List<ArticleEntity> {
        val findAll = dao.findAll()
        findAll.forEach {
            val builder = StringBuilder()
            val scanner = Scanner(it.content);

            var lineNum = 0
            while (scanner.hasNextLine()) {

                var line = scanner.nextLine().dropWhile {
                    " #*>`".contains(it)
                }
                if (line.isBlank()) {
                    continue
                }
                builder.append(line).append("<br>")
                if (lineNum == 3) break
                lineNum++
            }
            it.content = builder.toString()

        }
        return findAll;
    }

    fun getAllArticle():List<ArticleEntity> = dao.findAll();

    fun getOne(id: Long): ArticleEntity = dao.getOne(id)

    fun saveArticle(content: String): ArticleEntity {
        val articleWrapper = ArticleWrapper(content)
        val articleEntity = articleWrapper.createArticleEntity()
        val save = dao.save(articleEntity)
        return save
    }

}