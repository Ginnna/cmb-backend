package com.cmb.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_award")
public class Award {
    @Id
    private String id;

    private String name;

    private int count;

    private int probility;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getProbility() {
        return probility;
    }

    public void setProbility(int probility) {
        this.probility = probility;
    }
}
