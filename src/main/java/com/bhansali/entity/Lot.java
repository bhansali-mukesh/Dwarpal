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
@Table(name = "LOT")
public class Lot implements Cloneable {

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	@NotNull
	@OneToOne
	@JoinColumn(name = "CONSIGNMENT")
	private Consignment consignment;

	@NotNull
	@OneToOne
	@JoinColumn(name = "INVENTORY")
	private Inventory inventory;

	@NotNull
	@Column(name = "QUANTITY")
	private Integer quantity;

	@Column(name = "AMOUNT")
	private Double amount;

	@OneToOne
	@JoinColumn(name = "AGENT")
	private Person agent;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Consignment getConsignment() {
		return consignment;
	}

	public void setConsignment(Consignment consignment) {
		this.consignment = consignment;
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
		if (quantity == null || quantity <= 0)
			quantity = 1;
		this.quantity = quantity;
	}

	public Double getAmount() {
		if (amount == null || amount < 0)
			amount = 0.0;
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Person getAgent() {
		return agent;
	}

	public void setAgent(Person agent) {
		this.agent = agent;
	}

	public Lot getClone() {

		Lot lot = null;
		try {
			lot = (Lot) clone();
			lot.setId(null);
		} catch (CloneNotSupportedException cloneNotSupportedException) {
			cloneNotSupportedException.printStackTrace();
		}

		return lot;
	}
}