package org.example.model.impl;

import lombok.Data;
import org.example.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity(name = "city")
@Table(name = "city")
public class City extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "info")
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
