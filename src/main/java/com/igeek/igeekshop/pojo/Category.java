package com.igeek.igeekshop.pojo;

public class Category {
    private Integer cagegoryId;

    private String name;

    private String description;

    public Integer getCagegoryId() {
        return cagegoryId;
    }

    public void setCagegoryId(Integer cagegoryId) {
        this.cagegoryId = cagegoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}