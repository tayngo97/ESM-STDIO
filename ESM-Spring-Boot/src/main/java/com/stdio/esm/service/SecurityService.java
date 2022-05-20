package com.stdio.esm.service;


import com.stdio.esm.config.provider.JwtTokenProvider;
import com.stdio.esm.config.service.AccountDetail;
import com.stdio.esm.dto.request.LoginRequestDto;
import com.stdio.esm.dto.response.RefreshTokenRequestDto;
import com.stdio.esm.exception.EsmException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public RefreshTokenRequestDto login(LoginRequestDto request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())
                );
        return tokenProvider.generatedLoginResponse(((AccountDetail) authentication.getPrincipal()));
    }
    public String accessTokenReisue(RefreshTokenRequestDto request){

        if(!tokenProvider.validateToken(request.getRefreshToken())){
            throw new EsmException(EsmException.BAD_REQUEST);
        }
        if(request.getExpiredAt().compareTo(Instant.now())<0){
            throw new EsmException(EsmException.EXPIRED_REFRESH_TOKEN);
        }
        return tokenProvider.generatedAccessJwtFromRefreshJwt(request.getRefreshToken());
    }
}
