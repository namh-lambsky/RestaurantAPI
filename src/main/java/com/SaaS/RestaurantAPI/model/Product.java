package com.SaaS.RestaurantAPI.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "product")
@JsonIdentityInfo(scope =Product.class , generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@NoArgsConstructor @AllArgsConstructor
@Setter
public class Product {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "unitary_price")
    private long unitary_price;

    @Column(name = "iva")
    private boolean IVA;

    @Column(name = "total")
    private double total;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getUnitary_price() {
        return unitary_price;
    }

    public boolean isIVA() {
        return IVA;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public double getTotal() {
        if(this.isIVA()){
            this.total=this.unitary_price*0.19;
        }
        else{
            this.total=this.unitary_price;
        }
        return total;
    }

}
