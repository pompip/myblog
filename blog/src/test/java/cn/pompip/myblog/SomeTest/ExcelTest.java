package cn.pompip.myblog.SomeTest;

import cn.pompip.myblog.utils.ExcelUtil;
import cn.pompip.myblog.utils.LogUtil;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class ExcelTest {
    @Test
    public void testRead(){
        try {
            List s  = ExcelUtil.readExcel(new File("C:\\Users\\chong\\Desktop\\comsumer.xlsx"));
            s.forEach(line->{
                LogUtil.logo(line);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
