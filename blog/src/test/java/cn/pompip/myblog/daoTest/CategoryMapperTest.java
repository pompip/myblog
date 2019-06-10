//package cn.pompip.myblog.daoTest;
//
//import cn.pompip.myblog.mapper.CategoryMapper;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//
//public class CategoryMapperTest {
//
//    private SqlSession sqlSession;
//    private CategoryMapper mapper;
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    @Before
//    public void createDB() throws IOException {
//        sqlSession = new DataSourceCreater().getSqlSession();
//        mapper = sqlSession.getMapper(CategoryMapper.class);
//    }
//
//    @Test
//    public void testFindAll() throws JsonProcessingException {
//
//            System.out.println(objectMapper.writeValueAsString(mapper.findAllCategory()));
//
//    }
//}
