package cn.pompip.mybill.SomeTest;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MyTest {


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


}
