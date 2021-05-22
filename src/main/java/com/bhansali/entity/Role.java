package com.bhansali.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(name = "NAME")
    private String name;
   
    @NotNull
    @Column(name = "KEY")
    private Integer key;
    
	@Column(name = "DESCRIPTION")
    private String description;

	public Role() {}
	
	public Role(String name, Integer key, String description) {
		this.name = name;
		this.key = key;
		this.description = description;
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

    public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
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
}