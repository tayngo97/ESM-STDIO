package com.stdio.esm.dto.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Getter
@Setter
public class RefreshTokenRequestDto {

    @NotBlank
    private String accessToken;

    @NotBlank
    private String refreshToken;

    @NotBlank
    private Instant expiredAt;

    @NotBlank
    private Long id;
}
