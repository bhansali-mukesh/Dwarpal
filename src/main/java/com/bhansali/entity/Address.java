package com.bhansali.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @NotNull
    //@Size(min=1, message="Please Enter Your Location Id")
    @OneToOne
    @JoinColumn(name = "SOIL")
    private Soil soil;

    @Column(name = "zip")
    private Integer zip;

    @Column(name = "DESCRIPTION")
    private String description;

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString(){
        return "\n\tLocation : " + soil + "\nzip : " + zip + "\nDescription : " + description;
    }
}