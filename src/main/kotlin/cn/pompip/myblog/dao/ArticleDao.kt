package cn.pompip.myblog.dao

import cn.pompip.myblog.entity.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleDao :JpaRepository<ArticleEntity,Long> {
}