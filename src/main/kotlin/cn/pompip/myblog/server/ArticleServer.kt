package cn.pompip.myblog.server

import cn.pompip.myblog.dao.ArticleDao
import cn.pompip.myblog.entity.ArticleEntity
import cn.pompip.myblog.exe.ArticleWrapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.util.*


@Service
class ArticleServer {
    @Autowired
    lateinit var dao: ArticleDao;

    fun getIndexArticleList(): List<ArticleEntity> {
        val findAll = dao.findAll(Sort.by(Sort.Direction.DESC, "createTimestamp"))
        findAll.forEach {
            generateBrief(it)
        }
        return findAll;
    }

    private fun generateBrief(articleEntity: ArticleEntity) {
        val builder = StringBuilder()
        val scanner = Scanner(articleEntity.content);
        var lineNum = 0
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine().dropWhile {
                " #*<>`/".contains(it)
            }
            if (line.isBlank()) {
                continue
            }
            builder.append(line).append("<br>")
            if (lineNum == 3) break
            lineNum++
        }
        articleEntity.content = builder.toString()
    }

    fun getAllArticle(): List<ArticleEntity> = dao.findAll();

    fun getOne(id: Long): ArticleEntity = dao.getOne(id)

    fun saveArticle(content: String): ArticleEntity {
        val articleWrapper = ArticleWrapper(content)
        val articleEntity = articleWrapper.createArticleEntity()
        val save = dao.save(articleEntity)
        return save
    }

    fun updateArticle(content: String, id: Long) :ArticleEntity{
        val entity = getOne(id)
        entity.content = content;
        entity.updateTimestamp = Timestamp(System.currentTimeMillis())
        dao.save(entity);
        return entity;
    }

}