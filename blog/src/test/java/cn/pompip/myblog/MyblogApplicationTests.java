package cn.pompip.myblog;

import cn.pompip.myblog.entity.ArticleEntity;
import cn.pompip.myblog.mapper.ArticleMapper;
import cn.pompip.myblog.server.ArticleServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Consumer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {
    @Test
    public void contextLoads() {
    }

    @Autowired
    ArticleServer articleServer;

    @Test
    public void testIndex() {
        articleServer.getIndexArticleList().forEach(it -> System.out.print(it.getTitle()));
    }



    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void findSome(){
        articleMapper.findAll().forEach(new Consumer<ArticleEntity>() {
            @Override
            public void accept(ArticleEntity articleEntity) {
                System.out.println(articleEntity.getTitle());
            }
        });
    }
}
