package com.bhansali.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//@Embeddable
@Entity
@Table(name = "INVENTORY")
public class Inventory {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Column(name = "ITEM")
	private Integer item;

	@NotNull
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "DETAIL")
	private InventoryDetail detail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public InventoryDetail getDetail() {
		return detail;
	}

	public void setDetail(InventoryDetail detail) {
		this.detail = detail;
	}

	public String getName() {
		return detail.getName();
	}

	public void setName(String name) {
		detail.setName(name);
	}

	public Type getType() {
		return detail.getType();
	}

	public void setType(Type type) {
		detail.setType(type);
	}

	public String getColor() {
		return detail.getColor();
	}

	public void setColor(String color) {
		detail.setColor(color);
	}

	public Double getSize() {
		return detail.getSize();
	}

	public void setSize(Double size) {
		detail.setSize(size);
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Inventory))
			return false;
		Inventory inventory = (Inventory) object;

		return this.item == inventory.getItem() && this.detail.equals(inventory.getDetail());
	}
}