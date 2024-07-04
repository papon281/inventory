package com.inventory.model.db.product;

import com.inventory.constant.db.DbProduct;
import com.inventory.constant.db.DbUsers;
import com.inventory.constant.enums.Status;
import com.inventory.model.db.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = DbProduct.TABLE_NAME)
public class Product implements Serializable {
    @Id
    @UuidGenerator
    @Column(name = DbProduct.ID, nullable = false)
    private String id;

    @Column(name = DbProduct.PRODUCT_ID, nullable = false)
    private String productId;

    @Column(name = DbProduct.PRODUCT_NAME, nullable = false, unique = true)
    private String productName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = DbProduct.STATUS)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbProduct.CREATED_BY, nullable = false, updatable = false, referencedColumnName = DbUsers.EMAIL)
    private User createdBy;

    @CreationTimestamp
    @Column(name = DbProduct.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbProduct.LAST_UPDATED_BY, referencedColumnName = DbUsers.EMAIL)
    private User lastUpdatedBy;

    @UpdateTimestamp
    @Column(name = DbProduct.LAST_UPDATED_ON)
    private ZonedDateTime lastUpdatedOn;
}