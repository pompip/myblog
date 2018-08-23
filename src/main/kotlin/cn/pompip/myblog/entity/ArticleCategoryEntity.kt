package cn.pompip.myblog.entity

import java.sql.Timestamp
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
@Entity
data class ArticleCategoryEntity(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        var id:Int,
        val name :String,
        val position:Int,
        val createTimestamp: Timestamp
)