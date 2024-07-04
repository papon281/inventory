package com.inventory.model.db.category;

import com.inventory.constant.db.DbCategory;
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
@Entity(name = DbCategory.TABLE_NAME)
public class Category implements Serializable {
    @Id
    @UuidGenerator
    @Column(name = DbCategory.ID, nullable = false)
    private String id;

    @Column(name = DbCategory.CATEGORY_ID, nullable = false)
    private String categoryId;

    @Column(name = DbCategory.CATEGORY_NAME, nullable = false, unique = true)
    private String categoryName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = DbCategory.STATUS)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbCategory.CREATED_BY, nullable = false, updatable = false, referencedColumnName = DbUsers.EMAIL)
    private User createdBy;

    @CreationTimestamp
    @Column(name = DbCategory.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbCategory.LAST_UPDATED_BY, referencedColumnName = DbUsers.EMAIL)
    private User lastUpdatedBy;

    @UpdateTimestamp
    @Column(name = DbCategory.LAST_UPDATED_ON)
    private ZonedDateTime lastUpdatedOn;
}