package cn.pompip.lib.dao;


import cn.pompip.lib.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<ArticleEntity,Long> {
}
