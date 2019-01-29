package cn.pompip.myblog.entity;

import java.sql.Timestamp;
import java.util.List;

public class ArticleCategoryEntity {

    private int id;
    private String name;
    private int position;
    private Timestamp createTimestamp;


    private List<ArticleEntity> articleEntityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
}
