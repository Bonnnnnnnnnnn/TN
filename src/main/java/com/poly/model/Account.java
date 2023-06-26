package com.poly.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account {
	@Id
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String photo;
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "account")
	List<Authority> authorities;
}
