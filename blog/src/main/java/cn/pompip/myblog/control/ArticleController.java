package cn.pompip.myblog.control;

import cn.pompip.myblog.entity.ArticleEntity;
import cn.pompip.myblog.model.WebPage;
import cn.pompip.myblog.server.ArticleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
//@CrossOrigin
public class ArticleController {
    private ArticleServer server;

    @Autowired
    public ArticleController(ArticleServer server) {
        this.server = server;
    }

    @GetMapping("/all")
    public List<ArticleEntity> getArticleList() {
        return server.getAllArticle();
    }

    @GetMapping("/page/{page}")
    public WebPage<ArticleEntity> getArticleWithPage(@PathVariable int page) {
        return server.getArticleListWithPage(page);
    }

    @GetMapping("/detail/{id}")
    public ArticleEntity getArticle(@PathVariable long id) {
        return server.getArticle(id);
    }

    @PostMapping("/save")
    public ArticleEntity saveArticle( @RequestBody ArticleEntity articleEntity) {
        return server.saveArticle(articleEntity);
    }


    @GetMapping("/delete/{id}")
    public void deleteArticle(@PathVariable long id) {
        server.deleteArticle(id);
    }
}