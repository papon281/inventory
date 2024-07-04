package com.inventory.model.db.country;

import com.inventory.constant.db.DbCountry;
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
@Entity(name = DbCountry.TABLE_NAME)
public class Country implements Serializable {
    @Id
    @UuidGenerator
    @Column(name = DbCountry.ID, nullable = false)
    private String id;

    @Column(name = DbCountry.COUNTRY_ID, nullable = false)
    private String countryId;

    @Column(name = DbCountry.COUNTRY_NAME, nullable = false, unique = true)
    private String countryName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = DbCountry.STATUS)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbCountry.CREATED_BY, nullable = false, updatable = false, referencedColumnName = DbUsers.EMAIL)
    private User createdBy;

    @CreationTimestamp
    @Column(name = DbCountry.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DbCountry.LAST_UPDATED_BY, referencedColumnName = DbUsers.EMAIL)
    private User lastUpdatedBy;

    @UpdateTimestamp
    @Column(name = DbCountry.LAST_UPDATED_ON)
    private ZonedDateTime lastUpdatedOn;
}