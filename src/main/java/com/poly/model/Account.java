package com.poly.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	private static final long serialVersionUID= 1L;
	@Id
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Fullname")
	private String fullname;
	
	@Column(name = "Email")
	private String Email;
	
	@Column(name = "Photo")
	private String photo;
	
	@Column(name = "auth_provider")
	private String auth_provider;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "account")
	List<Authority> authorities;
}