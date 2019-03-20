package cn.pompip.myblog;

import cn.pompip.myblog.dao.IncomeDao;
import cn.pompip.myblog.entity.ArticleEntity;
import cn.pompip.myblog.entity.IncomeEntity;
import cn.pompip.myblog.mapper.ArticleMapper;
import cn.pompip.myblog.server.ArticleServer;
import cn.pompip.myblog.utils.JsonUtil;
import cn.pompip.myblog.utils.LogUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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

    @Autowired
    IncomeDao incomeDao;
    @Test public void testIncoDao(){

        List o = incomeDao.findAllByMonth(2019,2);
        LogUtil.logo(o);
    }


    @Autowired
    JsonUtil jsonUtil;
    @Autowired
    LogUtil logUtil;

    @Test
    public void testJsonUtil() {
        IncomeEntity incomeEntity = new IncomeEntity();
        incomeEntity.setMoney(11.11);
        incomeEntity.setWay("hello");
        String json = jsonUtil.toJson(incomeEntity);
        logUtil.e(json);

        System.out.println("---------");
        IncomeEntity entity = jsonUtil.toObject(json,IncomeEntity.class);
        logUtil.e(entity.getWay());
    }
}
