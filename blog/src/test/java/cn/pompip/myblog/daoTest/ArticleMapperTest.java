package cn.pompip.myblog.daoTest;

import cn.pompip.myblog.entity.ArticleEntity;
import cn.pompip.myblog.mapper.ArticleMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ArticleMapperTest {
    ArticleMapper mapper;

    ObjectMapper objectMapper = new ObjectMapper();
    @Before
    public void createDatasource() throws IOException {
        SqlSession sqlSession = new DataSourceCreater().getSqlSession();
         mapper = sqlSession.getMapper(ArticleMapper.class);

    }
    @Test
    public void testArticleMapper() {

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle("hello wolrd");
        articleEntity.setContent("# hello wolrd");
        articleEntity.setCategoryId(1);
        articleEntity.setAuthorId(2);
        long insert = mapper.insert(articleEntity);
        System.out.println(insert);
    }
    @Test
    public void testFindArticleWithAuthor() throws JsonProcessingException {

        ArticleEntity insert = mapper.findArticleWithAuthorById(5);
        System.out.println(objectMapper.writeValueAsString(insert));
    }

    @Test
    public void testFindAllLimit() throws JsonProcessingException {

        List<ArticleEntity> insert = mapper.findAllLimit(1);
        System.out.println(objectMapper.writeValueAsString(insert));
    }
}
