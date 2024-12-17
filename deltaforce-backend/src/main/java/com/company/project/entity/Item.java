package com.company.project.entity;

import lombok.Data;

@Data
public class Item {

    private Integer id;
    private String name;
    private Integer quality;
    private String desc;
    private Float price;
    private String img;
    private String source;
}
