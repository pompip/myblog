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
        model.addAttribute("categoryList", categoryService.findAllCategory());
        return "index.html";
    }


}