package com.fahmi.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mst_partner")
public class Partner {
    @Id
    private String id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "id_card_number")
    private String idCardNumber;

    @Column(name = "bank_name", nullable = true)
    private String bankName;

    @Column(name = "bank_account_name", nullable = true)
    private String bankAccountName;

    @Column(name = "bank_account_number", nullable = true)
    private String bankAccountNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "is_verified", nullable = false)
    private boolean isVerified;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
