package com.capstone.cart.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kitchenStaff")
public class KitchenStaff {
    @Id
    Long userId;
    String foodName;
    Boolean status;

}
