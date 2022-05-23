package com.stdio.esm.config.provider;

import com.stdio.esm.config.ApplicationProperties;
import com.stdio.esm.config.service.AccountDetail;
import com.stdio.esm.dto.response.RefreshTokenRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */

@Service
public class JwtTokenProvider implements Serializable {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";
    @Autowired
    private ApplicationProperties properties;

    public String generatedAccessJwt(AccountDetail accountDetail) {
        Date date = new Date();
        String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
        return Jwts.builder()
                .setSubject(accountDetail.getUsername())
                .claim("roles", accountDetail.getAuthorities())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + properties.getSecurity().getDurationAccessToken()))
                .signWith(SignatureAlgorithm.HS512, encodeSecret)
                .compact();
    }
    public String generatedRefreshJwt(AccountDetail accountDetail){
        Date date = new Date();
        String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
        return Jwts.builder()
                .setSubject(accountDetail.getUsername())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime()+properties.getSecurity().getDurationRefreshToken()))
                .signWith(SignatureAlgorithm.HS512,encodeSecret)
                .compact();

    }

    public RefreshTokenRequestDto generatedLoginResponse(AccountDetail accountDetail){
        RefreshTokenRequestDto loginResponseDto = new RefreshTokenRequestDto();
        loginResponseDto.setId(accountDetail.getId());
        loginResponseDto.setExpiredAt(Instant.now().plusMillis(properties.getSecurity().getDurationRefreshToken()));
        loginResponseDto.setAccessToken(generatedAccessJwt(accountDetail));
        loginResponseDto.setRefreshToken(generatedRefreshJwt(accountDetail));
        return loginResponseDto;
    }

    public boolean verifyExpirationAccessJwt(String accessToken){
        String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
        Date expiredDate = Jwts.parser().setSigningKey(encodeSecret).parseClaimsJws(accessToken).getBody().getExpiration();
        if(expiredDate.compareTo(Date.from(Instant.now()))<0){
            return false;
        }
        return true;
    }

    public String generatedAccessJwtFromRefreshJwt(String refreshToken){
        String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
        Claims encodeInformation = Jwts.parser().setSigningKey(encodeSecret).parseClaimsJws(refreshToken).getBody();
        Date date = new Date();
        return Jwts.builder()
                .setSubject(encodeInformation.getSubject())
                .claim("roles", encodeInformation.get("roles"))
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + properties.getSecurity().getDurationAccessToken()))
                .signWith(SignatureAlgorithm.HS512, encodeSecret)
                .compact();
    }


    public String getUsername(String token) {
        String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
        return Jwts.parser()
                .setSigningKey(encodeSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            String encodeSecret = TextCodec.BASE64.encode(properties.getSecurity().getPasswordSecret());
            Jwts.parser().setSigningKey(encodeSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX + " ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
