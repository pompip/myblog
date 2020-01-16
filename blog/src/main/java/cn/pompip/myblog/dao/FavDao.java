package cn.pompip.myblog.dao;

import cn.pompip.myblog.entity.FavEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavDao extends JpaRepository<FavEntity,Long> {
}
