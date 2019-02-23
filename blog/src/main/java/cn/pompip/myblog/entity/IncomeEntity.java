package cn.pompip.myblog.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
@Table(name = "income")
@Entity
public class IncomeEntity {
    @Id()
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private long id;

    private Double money;

    private Date date;

    private Timestamp createTime;

    private String way;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
}
