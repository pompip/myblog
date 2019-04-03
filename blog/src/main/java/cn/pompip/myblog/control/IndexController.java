package cn.pompip.myblog.control;

import cn.pompip.myblog.entity.ArticleEntity;
import cn.pompip.myblog.model.WebPage;
import cn.pompip.myblog.server.ArticleServer;

import static cn.pompip.myblog.utils.LogUtil.loge;

import cn.pompip.myblog.server.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    @Autowired
    ArticleServer articleServer;
    @Autowired
    CategoryService categoryService;

        @GetMapping("/")
    public String index(Model model) {
        WebPage<ArticleEntity> webPage = articleServer.getArticleListWithPage(0);
        model.addAttribute("articleList", webPage.getContent());
        model.addAttribute("webPages", webPage);
        model.addAttribute("categoryList",categoryService.findAllCategory());
        return "index";
    }


    public String page(@PathVariable int page, Model model) {
        WebPage<ArticleEntity> webPage = articleServer.getArticleListWithPage(page);
        model.addAttribute("articleList", webPage.getContent());
        model.addAttribute("webPages", webPage);
        return "index";
    }

//    @GetMapping("/markdown")
    public String startMarkdown(Model model) {
        return "markdown";
    }

//    @GetMapping("/archives")
    public String getArchives(Model model) {
        model.addAttribute("articleList", articleServer.getAllArticle());
        return "archives";
    }

//    @GetMapping("/archive/{id}")
    public String previewArticle(@PathVariable long id, Model model) {
        model.addAttribute("article", articleServer.getArticleHTML(id));
        return "article_content";
    }

//    @GetMapping("/markdown/{id}")
    public String startMarkdown(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        loge("id:" + id);
        return "markdown";
    }

//    @RequestMapping(value = {"/editor/{id}","/editor"})
    public String startEditor(@PathVariable(value = "id",required = false) Long id, Model model) {
        if (id != null) {
            model.addAttribute("id", id);
        }
        loge("id:" + id);
        return "editor";
    }

//    @GetMapping("/resume")
    public String myResume() {
        return "resume";
    }

}