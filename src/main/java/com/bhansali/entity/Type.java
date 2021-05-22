package com.bhansali.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TYPE")
public class Type {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Size(min=2, message="Please Enter Business ( Minimum 2 letter ) ")
    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object object) {
    	if( !( object instanceof Type ) ) return false;
    	Type type = ( Type ) object;
    	
        return name.equals(type.getName())  && description.equals(type.getDescription());
    }
}