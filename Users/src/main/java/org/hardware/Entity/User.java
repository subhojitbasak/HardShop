package org.hardware.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "user_table")
@Data
public class User {
    @Id
    @GeneratedValue
    private UUID userId;
    private String userName;

    private String email;
    private String phone;
    @Lob
    private String address;

}
