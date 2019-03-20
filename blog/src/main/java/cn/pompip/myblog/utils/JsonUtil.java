package cn.pompip.myblog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonUtil {
    @Autowired
    ObjectMapper objectMapper;

    public String toJson(Object object) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> T toObject(String json,Class<T> type) {
        T t = null;
        try {
            t = objectMapper.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }
}
