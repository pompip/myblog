package cn.pompip.myblog.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @ResponseBody
    @PostMapping("/login_in")
    public int login(String name, String pwd, HttpSession session) {
        if ("chong".equals(name) && pwd.equals("314159")) {
            session.setAttribute("user", "chong");
            return 1;
        } else {
            return 0;
        }
    }

    @ResponseBody
    @PostMapping("/logout")
    public int logout(HttpSession session) {
        session.removeAttribute("user");
        return 1;
    }

}