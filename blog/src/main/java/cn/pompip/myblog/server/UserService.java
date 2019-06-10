package cn.pompip.myblog.server;


import org.springframework.stereotype.Service;

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


}
