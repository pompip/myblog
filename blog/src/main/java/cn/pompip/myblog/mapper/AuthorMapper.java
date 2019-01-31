package cn.pompip.myblog.mapper;

import cn.pompip.myblog.entity.AuthorEntity;

public interface AuthorMapper {

    AuthorEntity findAuthorById(long id);

    long insertAuthor(String name);

    int updateAuthor(String name ,long id);

    int deleteAuthor(long id);

    AuthorEntity findAuthorWithArticle(long id);
}
