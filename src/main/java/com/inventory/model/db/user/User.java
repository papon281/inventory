package com.inventory.model.db.user;

import com.inventory.constant.db.DbUsers;
import com.inventory.constant.enums.Gender;
import com.inventory.constant.enums.Status;
import jakarta.persistence.*;
import lombok.*;
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
@Entity(name = DbUsers.TABLE_NAME)
@ToString
public class User implements Serializable {
    @Id
    @UuidGenerator
    @Column(name = DbUsers.ID, nullable = false)
    private String id;

    @Column(name = DbUsers.NAME, nullable = false)
    private String name;

    @Column(name = DbUsers.EMAIL, nullable = false, unique = true)
    private String email;

    @Column(name = DbUsers.PASSWORD, nullable = false)
    private String password;

    @Column(name = DbUsers.MOBILE_NUMBER, nullable = false)
    private Long mobileNumber;

    @Column(name = DbUsers.DATE_OF_BIRTH, nullable = false)
    private ZonedDateTime dateOfBirth;

    @Enumerated(value = EnumType.STRING)
    @Column(name = DbUsers.GENDER, nullable = false)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    @Column(name = DbUsers.STATUS)
    private Status status;

    @CreationTimestamp
    @Column(name = DbUsers.CREATED_ON, nullable = false, updatable = false)
    private ZonedDateTime createdOn;

    @UpdateTimestamp
    @Column(name = DbUsers.LAST_UPDATED_ON)
    private ZonedDateTime updatedOn;
}