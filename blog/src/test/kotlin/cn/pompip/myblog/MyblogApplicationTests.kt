package cn.pompip.myblog

import cn.pompip.myblog.server.ArticleServer
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class MyblogApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Autowired lateinit var articleServer: ArticleServer
    @Test
    fun testIndex(){
        articleServer.getIndexArticleList().forEach {
            print(it.title)
        };
    }

}
