package org.hardware.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue
    private UUID orderId;

    private UUID userId;
    private UUID productId;
    @Column(nullable = false)
    private Date orderDate;
//    private Products product;
    private String status;
    private String productsName;
    private int orderQty;
    private int totalAmount;
}
