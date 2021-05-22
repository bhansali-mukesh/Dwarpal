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

@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "LOGIN")
    private Login creator; 
    
    @Column(name = "COMMENT")
    private String comment;
       
    @Column(name = "TIME")
    private Date time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Login getCreator() {
		return creator;
	}

	public void setCreator(Login creator) {
		this.creator = creator;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}