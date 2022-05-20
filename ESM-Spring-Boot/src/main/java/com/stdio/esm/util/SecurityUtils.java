package com.stdio.esm.util;


import com.stdio.esm.config.service.AccountDetail;
import com.stdio.esm.exception.EsmException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class SecurityUtils {

    public static Optional<AccountDetail> getCurrentAccountLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication()).map(authentication -> {
            if (authentication.getPrincipal() instanceof AccountDetail) {
                return (AccountDetail) authentication.getPrincipal();
            }
            return null;
        });
    }

    public static AccountDetail getCurrentAccountOrElseThrow() {
        return getCurrentAccountLogin().orElseThrow(() -> new EsmException(EsmException.USER_NOT_FOUND));
    }

}
