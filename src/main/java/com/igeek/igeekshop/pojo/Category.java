package com.igeek.igeekshop.pojo;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Category {
    private Integer categoryId;

    private String name;

    private String description;

}