package com.stdio.esm.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Entity
@Table(name = "account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", length = 255, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "create_at",nullable = false,updatable = false)
    private Instant created_at;

    @UpdateTimestamp
    @Column(name = "modify_at",nullable = false)
    private Instant modify_at;

    @Column(name ="delete_flag")
    private Boolean delete_flag;

    @OneToMany(mappedBy = "account")
    List<AccountRole> accountRoleList;

    @OneToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false, updatable = false,insertable = false)
    private Employee employee;

}
