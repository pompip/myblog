package cn.pompip.myblog.exe

import cn.pompip.myblog.entity.ArticleEntity
import java.sql.Timestamp
import java.util.*

class ArticleWrapper( con: String) {
    var title: String = ""
    var content: String
    var createTimestamp: Timestamp;

    init {
        val scanner = Scanner(con)
        val line = scanner.nextLine()
        if (line != null && line.startsWith("# ")) {
            this.title = line.substring(2)
        } else {
            title = "无标题"
        }

        content = con;
        createTimestamp = Timestamp(System.currentTimeMillis())
    }

    fun createArticleEntity(): ArticleEntity {
        val a = ArticleEntity();
        a.content = content;
        a.title = title;
        a.createTimestamp = createTimestamp;
        return a;

    }

}