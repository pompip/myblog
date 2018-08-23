package cn.pompip.myblog.entity

import java.sql.Date
import java.sql.Timestamp
import javax.persistence.*

@Entity
data class ArticleEntity(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        var id: Long =0,
        @Column(columnDefinition = "TEXT")
        var content: String ="",
        var createTimestamp: Timestamp =Timestamp(System.currentTimeMillis()),
        var updateTimestamp:Timestamp = Timestamp(System.currentTimeMillis()),
        var authorId:Long =0,
        var categoryId:Int =0,
        var title:String=""
        )