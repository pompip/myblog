package cn.pompip.myblog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestEntity {
    @Id
    @GeneratedValue
    private long id;
    private String name;
}
