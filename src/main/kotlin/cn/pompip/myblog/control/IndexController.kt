package cn.pompip.myblog.control

import cn.pompip.myblog.server.ArticleServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class IndexController{
    @Autowired
    lateinit var server: ArticleServer


    @GetMapping("/")
     fun index(model:Model):String{
        val articleList = server.getAll();
        model.addAttribute("articleList",articleList)
        return "index"
    }

    @GetMapping("/article/{id}")
    fun articleContent(@PathVariable("id") id: Long, model: Model): String {
        model.addAttribute("article", server.getOne(id))
        return "article_content"
    }
}