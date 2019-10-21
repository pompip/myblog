package cn.pompip.myblog.SomeTest;

import cn.pompip.myblog.server.UserService;
import org.junit.Test;


public class JwtTest {
    @Test
    public void testGetToken(){
        UserService userService = new UserService();
        String chong = userService.getToken("chong");
        System.out.println(chong);
        userService.refreshToken(chong);
    }
}
