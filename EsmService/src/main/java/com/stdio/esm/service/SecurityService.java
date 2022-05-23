package com.stdio.esm.service;
import com.stdio.esm.config.provider.JwtTokenProvider;
import com.stdio.esm.config.service.AccountDetail;
import com.stdio.esm.dto.request.LoginRequestDto;
import com.stdio.esm.dto.response.RefreshTokenRequestDto;
import com.stdio.esm.exception.EsmException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Service
@RequiredArgsConstructor
public class SecurityService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private static final Logger LOGGER= LoggerFactory.getLogger(SecurityService.class);

    public RefreshTokenRequestDto login(LoginRequestDto request) {
        LOGGER.info("{} login into app", request.getUsername());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())
                );
        LOGGER.info("{} logged in successfully", request.getUsername());
        return tokenProvider.generatedLoginResponse(((AccountDetail) authentication.getPrincipal()));

    }
    public String accessTokenReisue(RefreshTokenRequestDto request){
        LOGGER.info("Get new AccessToken from RefreshToken");

        if(!tokenProvider.validateToken(request.getRefreshToken())){
            LOGGER.error("RefreshToken is not correct");
            throw new EsmException(EsmException.BAD_REQUEST);
        }
        if(request.getExpiredAt().compareTo(Instant.now())<0){
            LOGGER.trace("RefreshToken has expired");
            throw new EsmException(EsmException.EXPIRED_REFRESH_TOKEN);
        }
        LOGGER.info("Get new AccessToken from RefreshToken");
        return tokenProvider.generatedAccessJwtFromRefreshJwt(request.getRefreshToken());
    }
}
