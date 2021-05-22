package com.bhansali.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@Entity
@Table(name = "ORGANIZATION")
public class Organization {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Size(min=2, message="Please Enter Organization Name")
    @Column(name = "NAME")
    private String name;

    @NotNull
    @OneToOne
    @JoinColumn(name = "BUSINESS")
    private Business business;
    
    @NotNull
    @OneToOne
    @JoinColumn(name = "CATEGORY")
    private Category category;
    
    @Column(name = "GST")
    private String gst;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CONTACT")
    private Contact contact;

    @OneToOne
    @JoinColumn(name = "OWNER")
    private Person owner;
    
    @OneToOne
    @JoinColumn(name = "PARENT")
    private Organization parent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "SINCE")
    private Date since;
    
    @Column(name = "DELETED")
    private Boolean deleted;
    
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

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public Category getCategory() {
			return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
		
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public Date getSince() {
		return since;
	}

	public void setSince(Date since) {
		this.since = since;
	}
	
  public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = false;
    }
	
    public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	@Override
    public String toString() {
        return name;
    }
}