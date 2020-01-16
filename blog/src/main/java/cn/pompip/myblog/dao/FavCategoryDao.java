package cn.pompip.myblog.dao;

import cn.pompip.myblog.entity.FavCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavCategoryDao extends JpaRepository<FavCategoryEntity,Long> {
}
