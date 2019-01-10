package cn.pompip.myblog.control;

import cn.pompip.lib.entity.ArticleEntity;
import cn.pompip.myblog.server.ArticleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleServer server;

    @PostMapping("/list")
    public List<ArticleEntity> getArticleList() {
        return server.getAllArticle();
    }


    @PostMapping("/{id}")
    public ArticleEntity getArticle(@PathVariable long id) {
       return server.getOne(id);
    }

    @PostMapping("/save")
    public long saveArticle(String content) {
        return server.saveArticle(content).getId();
    }


    @PostMapping("/update")
    public long updateArticle(String content, long id) {
        return server.updateArticle(content, id).getId();
    }

    @PostMapping("/delete/{id}")
    public void deleteArticle(@PathVariable long id) {
        server.deleteArticle(id);
    }
}