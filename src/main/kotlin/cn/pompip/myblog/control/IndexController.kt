package cn.pompip.myblog.control

import cn.pompip.myblog.server.ArticleServer
import cn.pompip.myblog.utils.loge
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class IndexController {
    @Autowired
    lateinit var server: ArticleServer


    @GetMapping("/")
    fun index(model: Model): String {
        val articleList = server.getIndexArticleList();
        model.addAttribute("articleList", articleList)
        return "index"
    }


    @GetMapping("/markdown")
    fun startMarkdown(model: Model): String = "/markdown"



    @GetMapping("/archives")
    fun getArchives(model:Model):String{
        model.addAttribute("articleList",server.getAllArticle())
        return "/archives"
    }

    @GetMapping("/archive/{id}")
    fun previewArticle(@PathVariable id: Long,model:Model):String{
        model.addAttribute("article",server.getOne(id))
        return "/article_content"
    }

    @GetMapping("/markdown/{id}")
    fun startMarkdown(@PathVariable("id") id :Long,model:Model):String{
        model.addAttribute("id",id)
        loge("id:"+id.toString())
        return "/markdown";
    }
}