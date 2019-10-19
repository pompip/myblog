package cn.pompip.myblog.server;


import cn.pompip.myblog.entity.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService  {

    private Map<String,String> userToken = new ConcurrentHashMap<>();
    public void addUser(String token,String username){
        userToken.put(token,username);
    }

    public void remoteUser(String token){
        userToken.remove(token);
    }

    public boolean isRegist(String token){
        return userToken.get(token)==null;
    }


    public UserEntity findUserById(String userId) {
        return new UserEntity();
    }

    public String getToken(String name,String password) {
        return JWT.create().withAudience(name).withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60))
                .sign(Algorithm.HMAC256(password));
    }

    public UserEntity findUserByName(String name) {
        UserEntity userEntity =  new UserEntity();
        userEntity.setPassword("314159");
        userEntity.setName("chong");
        return userEntity;
    }
}
