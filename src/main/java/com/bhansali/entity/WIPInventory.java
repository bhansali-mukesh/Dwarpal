package com.bhansali.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "WIP")
public class WIPInventory {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "INVENTORY", unique = true)
    private Inventory inventory;

    @NotNull
    @Column(name = "QUANTITY")
    private Integer quantity;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
    public String toString() {
        return id.toString();
    }
}