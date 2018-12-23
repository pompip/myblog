package cn.pompip.admin.server;

import cn.pompip.lib.dao.ArticleDao;
import cn.pompip.lib.entity.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServer {

    @Autowired
    ArticleDao articleDao;

    public List<ArticleEntity> allArticles(){
        return articleDao.findAll();
    }

    public void saveAllArticle(){
        articleDao.saveAll(allArticles());
    }

}
