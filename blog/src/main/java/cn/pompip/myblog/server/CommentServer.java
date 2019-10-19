package cn.pompip.myblog.server;

import cn.pompip.myblog.dao.CommentDao;
import cn.pompip.myblog.entity.ArticleEntity;
import cn.pompip.myblog.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServer {
    @Autowired
    CommentDao commentDao;

    public List<CommentEntity> getArticleComment(long id) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setArticleId(id);
        Example<CommentEntity> example = Example.of(commentEntity);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("articleId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");
        Example<CommentEntity> example1 = Example.of(commentEntity, exampleMatcher);

        return commentDao.findAll(example1);
    }

    public CommentEntity postComment(CommentEntity commentEntity) {
        return commentDao.save(commentEntity);
    }
}
