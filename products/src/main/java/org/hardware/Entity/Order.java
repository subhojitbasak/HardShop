package org.hardware.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;


@Data
public class Order {


    private UUID orderId;

    private UUID userId;
    private UUID productId;
    private Date orderDate;
//    private Products product;
    private String status;
    private String productsName;
    private int orderQty;
    private int totalAmount;
}
