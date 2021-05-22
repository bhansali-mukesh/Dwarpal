package com.bhansali.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Embeddable
@Entity
@Table(name = "INVENTORY_DETAILS")
public class InventoryDetail {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

	@NotNull
    @Column(name = "NAME")
    private String name;

    @OneToOne
    @JoinColumn(name = "TYPE")
    private Type type;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "SIZE")
    private Double size;

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		if(size == null || size <= 0) size = 1.0;
		this.size = size;
	}
	
	@Override
	public boolean equals(Object object) {
		
		if( !( object instanceof InventoryDetail ) ) return false;
		InventoryDetail detail = ( InventoryDetail ) object;
    	
		return this.name.equals(detail.getName()) && 
				this.type.equals(detail.getType()) && 
				this.color.equals(detail.getColor()) && 
				this.size.equals(detail.getSize());
	}
}