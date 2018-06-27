package com.test.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class test {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer create_time;

    public test() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Integer create_time) {
        this.create_time = create_time;
    }
}
