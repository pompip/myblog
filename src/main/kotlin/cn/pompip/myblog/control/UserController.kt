package cn.pompip.myblog.control

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/user")
class UserController {

    @GetMapping("/login")
    fun login() = "login"

    @ResponseBody
    @PostMapping("/login")
    fun login(name: String, pwd: String, session: HttpSession) :Int{
        if ("chong" == name && pwd == "314159") {
            session.setAttribute("user", "chong")
            return 1
        }else{
            return 0
        }
    }
    @ResponseBody
    @PostMapping("/logout")
    fun logout( session: HttpSession) :Int{
            session.removeAttribute("user")
            return 1
    }

}