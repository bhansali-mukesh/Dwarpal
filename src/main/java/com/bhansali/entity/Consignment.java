package com.bhansali.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CONSIGNMENT")
public class Consignment implements Cloneable {

	public static enum Type {
		PURCHASE, PROCESS, PROCESSED, SELL, SALES_RETURN, PURCHASE_RETURN, DELETE, EDIT
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	@NotNull
	@OneToOne
	@JoinColumn(name = "SENDER")
	private Organization sender;

	@NotNull
	@OneToOne
	@JoinColumn(name = "RECEIVER")
	private Organization receiver;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATE")
	private Date date;

	@NotNull
	@Column(name = "TYPE")
	private Type type;

	@OneToOne
	@JoinColumn(name = "SOURCE")
	private Consignment previousConsignment;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "SYS_DATE")
	private Date systemDate;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "DELETED")
	private Boolean deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Organization getSender() {
		return sender;
	}

	public void setSender(Organization sender) {
		this.sender = sender;
	}

	public Organization getReceiver() {
		return receiver;
	}

	public void setReceiver(Organization receiver) {
		this.receiver = receiver;
	}

	public Date getDate() {
		// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		// return formatter.format(date);
		return date;
	}

	public Date getRawDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		if (type != null)
			this.type = type;

		System.out.println(" Type in Set is : " + type);
	}

	public String getComment() {
		return comment;
	}

	public Consignment getPreviousConsignment() {
		return previousConsignment;
	}

	public void setPreviousConsignment(Consignment previousConsignment) {
		this.previousConsignment = previousConsignment;
	}

	public Date getSystemDate() {
		return systemDate;
	}

	public void setSystemDate() {
		this.systemDate = new Date();
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {

		if (deleted == null)
			this.deleted = false;
		else
			this.deleted = deleted;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Consignment getClone() {

		Consignment consignment = null;

		try {
			consignment = (Consignment) clone();
			consignment.setId(null);
			consignment.setDate(new Date());
		} catch (CloneNotSupportedException cloneNotSupportedException) {
			cloneNotSupportedException.printStackTrace();
		}

		return consignment;
	}

	public Consignment() {
		date = new Date();
		systemDate = new Date();
	}

}