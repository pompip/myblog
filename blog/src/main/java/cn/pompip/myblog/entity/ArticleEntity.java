package cn.pompip.myblog.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
@Entity
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    private String content;
//    private Timestamp createTimestamp ;
    private long createTime;
//    private Timestamp updateTimestamp ;
    private long updateTime;
    private long authorId;
    private int categoryId;
    private String title;
    @Transient
    private AuthorEntity authorEntity;
    @Transient
    private CategoryEntity categoryEntity;


    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public AuthorEntity getAuthorEntity() {
        return authorEntity;
    }

    public void setAuthorEntity(AuthorEntity authorEntity) {
        this.authorEntity = authorEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public Timestamp getCreateTimestamp() {
//        return createTimestamp;
//    }
//
//    public void setCreateTimestamp(Timestamp createTimestamp) {
//        this.createTimestamp = createTimestamp;
//    }
//
//    public Timestamp getUpdateTimestamp() {
//        return updateTimestamp;
//    }
//
//    public void setUpdateTimestamp(Timestamp updateTimestamp) {
//        this.updateTimestamp = updateTimestamp;
//    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                '}';
    }
}
