package cn.pompip.myblog;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class TestUtil {
    @Test
    public void testStringFormat() throws UnsupportedEncodingException {
        String s = String.format("attachment; filename=\"%s\"",new String("hello".getBytes(), "ISO-8859-1"));
        System.out.println(s);
    }
}
