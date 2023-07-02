package com.poly.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String image;
	private Double price;
	@Column(name = "Productquantity")
	private Integer productQuantity;
	
	@Column(name = "Description")
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	private Date createDate = new Date();
	private Boolean available;
	private Boolean status;
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	private Category category;
//	@JsonIgnore
//	@OneToMany(mappedBy = "product")
//	List<OrderDetail> orderDetails;
	public void setProductId(Integer id2) {
		// TODO Auto-generated method stub
		
	}
}
