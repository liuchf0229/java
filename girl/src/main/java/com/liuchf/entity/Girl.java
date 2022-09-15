package com.liuchf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Entity -- 表示对应数据库内的一个表
 * @Id @GeneratedValue -- id自增
 * 右键generate生成get、set方法
 * */

@Entity
public class Girl {

    @Id
    @GeneratedValue
    private Integer id;

    private String cupSize;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public Girl() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
