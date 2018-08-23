package cn.pompip.myblog.entity

import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import javax.persistence.*

@Entity
data class UserEntity(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        var uid:Long,
        var name:String,
        var password:String,
        var nickName:String,
        var email :String,
        var phone:String,
        var createTimestamp: Timestamp,
        var updateTimestamp: Timestamp
)