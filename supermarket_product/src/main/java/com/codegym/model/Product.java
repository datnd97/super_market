package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String type;
    private String prince;
    private String count;
    private String date;
    private String content;
    @NotEmpty
    @Size(min = 2,max = 20)
    private String name;
    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrince() {
        return prince;
    }

    public void setPrince(String prince) {
        this.prince = prince;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Product(String type, String prince, String count, String date, String content, @NotEmpty @Size(min = 2, max = 20) String name) {
        this.type = type;
        this.prince = prince;
        this.count = count;
        this.date = date;
        this.content = content;
        this.name = name;
        }
        }
