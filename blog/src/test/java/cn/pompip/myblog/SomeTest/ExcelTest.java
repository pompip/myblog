//package cn.pompip.myblog.SomeTest;
//
//import cn.pompip.myblog.entity.ArticleEntity;
//import cn.pompip.myblog.entity.AuthorEntity;
//import cn.pompip.myblog.mapper.ArticleMapper;
//import cn.pompip.myblog.server.ArticleServer;
//import cn.pompip.myblog.utils.ExcelUtil;
//import cn.pompip.myblog.utils.LogUtil;
//import org.junit.Test;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Consumer;
//
//public class ExcelTest {
//    @Test
//    public void testRead(){
//        try {
//            List s  = ExcelUtil.readExcel(new File("C:\\Users\\chong\\Desktop\\comsumer.xlsx"));
//            s.forEach(line->{
//                LogUtil.logo(line);
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testBeanUtils() {
//        Map<String, Object> map = new HashMap<>();
//        AuthorEntity authorEntity = new AuthorEntity();
//        authorEntity.setName("hello");
//        authorEntity.setId(1);
//
//        BeanUtils.copyProperties(authorEntity, map, AuthorEntity.class);
//        System.out.println(map);
//    }
//
//    @Test
//    public void contextLoads() {
//    }
//
//    @Autowired
//    ArticleServer articleServer;
//
//    @Test
//    public void testIndex() {
//        articleServer.getIndexArticleList().forEach(it -> System.out.print(it.getTitle()));
//    }
//
//
//
//    @Autowired
//    ArticleMapper articleMapper;
//
//    @Test
//    public void findSome(){
//        articleMapper.findAll().forEach(new Consumer<ArticleEntity>() {
//            @Override
//            public void accept(ArticleEntity articleEntity) {
//                System.out.println(articleEntity.getTitle());
//            }
//        });
//    }
//
//}
