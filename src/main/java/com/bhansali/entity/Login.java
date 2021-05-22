package com.bhansali.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "LOGIN")
public class Login {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Size(min = 5, message = "Please Enter Username ( Minimum 5 letter ) ")
	@Column(name = "USERNAME", unique = true)
	private String username;

	@Size(min = 8, message = "Please Enter Password ( Minimum 8 letter ) ")
	@Column(name = "PASSWORD")
	private String password;

	@Transient
	@Size(min = 8, message = "Please Confirm Password ( Minimum 8 letter ) ")
	private String confirmPassword;

	@Size(min = 8, message = "Please Enter Power Phrase ( Minimum 8 letter ) ")
	@Column(name = "POWERPHRASE")
	private String powerPhrase;

	@Transient
	@Size(min = 8, message = "Please Confirm Power Phrase ( Minimum 8 letter ) ")
	private String confirmPowerPhrase;

	@OneToOne
	@JoinColumn(name = "ORGANIZATION")
	private Organization organization;

	@Column(name = "HINT")
	@Size(min = 6, message = "Please Enter Password ( Minimum 6 letter ) ")
	private String hint;

	// @NotNull
	// @OneToOne
	// @JoinColumn(name = "ROLE")
	private Integer role;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EXPIRY")
	private Date expiry;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPowerPhrase() {
		return powerPhrase;
	}

	public void setPowerPhrase(String powerPhrase) {
		this.powerPhrase = powerPhrase;
	}

	public String getConfirmPowerPhrase() {
		return confirmPowerPhrase;
	}

	public void setConfirmPowerPhrase(String confirmPowerPhrase) {
		this.confirmPowerPhrase = confirmPowerPhrase;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Date getExpiry() {
		return expiry;
	}

	public void setExpiry(Date expiry) {
		this.expiry = expiry;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}