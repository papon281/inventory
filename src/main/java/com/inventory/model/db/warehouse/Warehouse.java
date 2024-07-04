package com.inventory.model.db.warehouse;

import com.inventory.constant.db.DbUsers;
import com.inventory.constant.db.DbWarehouse;
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
@Entity(name = DbWarehouse.TABLE_NAME)
public class Warehouse implements Serializable {
    @Id
    @UuidGenerator
    @Column(name = DbWarehouse.ID, nullable = false)
    private String id;

    @Column(name = DbWarehouse.WAREHOUSE_ID, nullable = false)
    private String warehouseId;

    @Column(name = DbWarehouse.WAREHOUSE_NAME, nullable = false, unique = true)
    private String warehouseName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = DbWarehouse.STATUS)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbWarehouse.CREATED_BY, nullable = false, updatable = false, referencedColumnName = DbUsers.EMAIL)
    private User createdBy;

    @CreationTimestamp
    @Column(name = DbWarehouse.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbWarehouse.LAST_UPDATED_BY, referencedColumnName = DbUsers.EMAIL)
    private User lastUpdatedBy;

    @UpdateTimestamp
    @Column(name = DbWarehouse.LAST_UPDATED_ON)
    private ZonedDateTime lastUpdatedOn;
}