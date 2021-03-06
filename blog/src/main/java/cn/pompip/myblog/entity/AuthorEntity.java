package cn.pompip.myblog.entity;

import javax.persistence.*;
import java.util.List;
@Entity
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    private String name;
    @Transient
    private List<ArticleEntity> articleEntityList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ArticleEntity> getArticleEntityList() {
        return articleEntityList;
    }

    public void setArticleEntityList(List<ArticleEntity> articleEntityList) {
        this.articleEntityList = articleEntityList;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", articleEntityList=" + articleEntityList +
                '}';
    }
}
