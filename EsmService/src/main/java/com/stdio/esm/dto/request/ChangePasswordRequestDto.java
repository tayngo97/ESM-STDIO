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
public class ChangePasswordRequestDto {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String confirmNewPassword;
}
