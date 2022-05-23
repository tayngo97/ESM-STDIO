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
public class RegisterRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
