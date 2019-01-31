package cn.pompip.myblog.mapper;

import cn.pompip.myblog.entity.ArticleEntity;
import cn.pompip.myblog.entity.AuthorEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {

    @Select("select * from article_entity")
    @Results({@Result(column = "create_timestamp",property = "createTimestamp",jdbcType = JdbcType.TIMESTAMP,javaType = Timestamp.class),
            @Result(column = "update_timestamp",property = "updateTimestamp",jdbcType = JdbcType.TIMESTAMP,javaType = Timestamp.class)})
    List<ArticleEntity> findAll();

    @Select("select * from article_entity order by create_timestamp DESC limit #{page},10")
    @Results({@Result(column = "create_timestamp",property = "createTimestamp",jdbcType = JdbcType.TIMESTAMP,javaType = Timestamp.class),
            @Result(column = "update_timestamp",property = "updateTimestamp",jdbcType = JdbcType.TIMESTAMP,javaType = Timestamp.class)})
    List<ArticleEntity> findAllLimit(@Param("page") int page);

    @Select("select count(*) from article_entity")
    long count();

    @Update("update article_entity set content=#{content},update_timestamp = #{timestamp} where id = #{id}")
    int updateContent(@Param("id") long id,@Param("content") String content,@Param("timestamp") Timestamp timestamp);

    @Insert("insert into article_entity (content,title,author_id,category_id) values(#{content},#{title},#{authorId},#{categoryId})")
    @SelectKey(resultType = long.class,before = false ,keyProperty = "id",statement = "select LAST_INSERT_ID()")
    long insert( ArticleEntity entity);

    @Delete("delete from article_entity where id = #{id}")
    void deleteById(@Param("id") long id);

    @Select("select * from article_entity where id = #{id}")
    @Results({@Result(column = "create_timestamp",property = "createTimestamp",javaType = Timestamp.class),
            @Result(column = "update_timestamp",property = "updateTimestamp",javaType = Timestamp.class),})
    Optional<ArticleEntity> findById(long id);

    ArticleEntity findArticleWithAuthorById(long id);

}
