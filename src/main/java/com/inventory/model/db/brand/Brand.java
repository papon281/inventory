package com.inventory.model.db.brand;

import com.inventory.constant.db.DbBrand;
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
@Entity(name = DbBrand.TABLE_NAME)
public class Brand implements Serializable {
    @Id
    @UuidGenerator
    @Column(name = DbBrand.ID, nullable = false)
    private String id;

    @Column(name = DbBrand.BRAND_ID, nullable = false)
    private String brandId;

    @Column(name = DbBrand.BRAND_NAME, nullable = false, unique = true)
    private String brandName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = DbBrand.STATUS)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbBrand.CREATED_BY, nullable = false, updatable = false, referencedColumnName = DbUsers.EMAIL)
    private User createdBy;

    @CreationTimestamp
    @Column(name = DbBrand.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbBrand.LAST_UPDATED_BY, referencedColumnName = DbUsers.EMAIL)
    private User lastUpdatedBy;

    @UpdateTimestamp
    @Column(name = DbBrand.LAST_UPDATED_ON)
    private ZonedDateTime lastUpdatedOn;
}