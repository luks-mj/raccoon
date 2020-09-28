package com.mujun.mng.model;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer id;

    private String name; // 角色名称

    private String nameZh; // 中文名称

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
        this.name = name == null ? null : name.trim();
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }
}