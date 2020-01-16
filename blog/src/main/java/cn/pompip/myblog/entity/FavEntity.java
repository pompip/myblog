package cn.pompip.myblog.entity;

import javax.persistence.*;

@Entity
public class FavEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String url;
    private String icon;

    @Column(name = "category_id")
    private long categoryId;
    private long time;

    @ManyToOne()
    @JoinColumn(name = "category_id" ,insertable = false,updatable = false)
    private FavCategoryEntity favCategoryEntity;

    public FavCategoryEntity getFavCategoryEntity() {
        return favCategoryEntity;
    }

    public void setFavCategoryEntity(FavCategoryEntity favCategoryEntity) {
        this.favCategoryEntity = favCategoryEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "FavEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", categoryId=" + categoryId +
                ", time=" + time +
                ", favCategoryEntity=" + favCategoryEntity +
                '}';
    }
}
