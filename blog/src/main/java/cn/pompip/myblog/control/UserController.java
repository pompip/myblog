package cn.pompip.myblog.control;

import cn.pompip.myblog.server.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
//@CrossOrigin
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/logon")
    public String register() {
        return "register";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String name, @RequestParam String password) throws Exception {
//        String name = (String) map.get("name");
//        String password = (String) map.get("password");
        Map<String, String> tokenResponse = new HashMap<>();
        Logger.getLogger("").info("name:" + name + " pwd:" + password);
        if ("chong".equals(name) && password.equals("314159")) {
            String token = new Date().toString();
            userService.addUser(token, "chong");
            tokenResponse.put("token", token);
            return tokenResponse;
        } else {
            throw new RuntimeException();
        }

    }

    @PostMapping("/logout")
    public int logout(HttpServletRequest request) {
        String headToken = request.getHeader("token");
        userService.remoteUser(headToken);
        return 1;
    }

}