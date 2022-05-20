package com.stdio.esm.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "accounts_roles")
@Data
public class AccountRole {
    @EmbeddedId
    private AccountRoleId accountRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Role role;

    @Embeddable
    @Data
    public static class AccountRoleId implements Serializable {

        private static final long serialVersionUID = -8886468907100754072L;

        @Column(name = "account_id")
        private Long account_id;

        @Column(name = "role_id")
        private Long role_id;
    }

}
