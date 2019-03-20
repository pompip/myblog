package cn.pompip.myblog;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import cn.pompip.myblog.entity.AuthorEntity;
import cn.pompip.myblog.entity.IncomeEntity;
import cn.pompip.myblog.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyTest {
    @Test
    public void testBeanUtils() {
        Map<String, Object> map = new HashMap<>();
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setName("hello");
        authorEntity.setId(1);

        BeanUtils.copyProperties(authorEntity, map, AuthorEntity.class);
        System.out.println(map);
    }

    @Test
    public void testMatches() {
        String line = "version=65 ()\n" +
                "arch=arm64\n" +
                "minsdk=24\n" +
                "maxsdk=24";
        String[] split = line.split("\\n");

        List<String> strings = Arrays.asList(split);
        for (String s : strings) {
            Pattern r = Pattern.compile("^version=\\d*.*");
            Matcher m = r.matcher(s);

            System.out.println(m.group());
//            System.out.println(s.matches("^version=65.*"));
        }

    }

    @Autowired
    JsonUtil jsonUtil;

    @Test
    public void testJsonUtil() {
        IncomeEntity incomeEntity = new IncomeEntity();
        incomeEntity.setMoney(11.11);
        incomeEntity.setWay("hello");
        String json = jsonUtil.toJson(incomeEntity);
        System.out.println(json);
    }

}
