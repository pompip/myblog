package cn.pompip.myblog.dao;

import cn.pompip.myblog.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<AuthorEntity,Long> {
}
