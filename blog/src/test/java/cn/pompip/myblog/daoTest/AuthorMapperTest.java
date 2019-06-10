//package cn.pompip.myblog.daoTest;
//
//import cn.pompip.myblog.entity.AuthorEntity;
//import cn.pompip.myblog.mapper.AuthorMapper;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//
//
//public class AuthorMapperTest {
//    SqlSession sqlSession;
//    ObjectMapper mapper = new ObjectMapper();
//    AuthorMapper authorMapper;
//    @Before
//    public void MyTest() throws IOException {
//        sqlSession = new DataSourceCreater().getSqlSession();
//         authorMapper = sqlSession.getMapper(AuthorMapper.class);
//    }
//
//    @Test
//    public void testFindAuthor() throws JsonProcessingException {
//        AuthorEntity authorWithArticle = authorMapper.findAuthorWithArticle(1);
//
//        System.out.println(mapper.writeValueAsString(authorWithArticle));
//
//    }
//
//    @Test
//    public void deleteAuthor(){
//        authorMapper.deleteAuthor(1);
//    }
//
//    @Test
//    public void testUpdate(){
//        authorMapper.updateAuthor("hello",1);
//    }
//    @Test
//    public void testAuthorMapperInsert(){
//        AuthorEntity authorEntity = new AuthorEntity();
//        authorEntity.setName("chong_liu");
//        System.out.println(authorMapper.insertAuthor("hello world"));
//        System.out.println(authorEntity.getId());
//    }
//    @Test
//    public void testAuthorMapper(){
//        authorMapper.findAuthorById(1);
//    }
//
//
//
//
//}
