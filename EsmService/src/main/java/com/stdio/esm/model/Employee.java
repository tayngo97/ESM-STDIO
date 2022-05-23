package com.stdio.esm.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Entity
@Table(name ="employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "address",length = 255)
    private String address;

    @Column(name = "avatar_img")
    private String avatar;

    @Column(name = "start_date")
    private Instant start_date;

    @CreationTimestamp
    @Column(name = "create_at",nullable = false, updatable = false)
    private Instant created_at;

    @UpdateTimestamp
    @Column(name = "modify_at",nullable = false)
    private Instant modify_at;

    @Column(name ="delete_flag")
    private Boolean delete_flag;


}
