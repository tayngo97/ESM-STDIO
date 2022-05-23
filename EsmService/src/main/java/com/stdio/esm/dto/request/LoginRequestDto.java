package com.stdio.esm.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Getter
@Setter
public class LoginRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
