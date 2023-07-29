package com.poly.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private Double price;
    private int quality;

    @Temporal(TemporalType.DATE)
    @Column(name = "Applyday")
    private Date applyDay = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "Expiration")
    private Date expiration = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    private Date createdate = new Date();
}
