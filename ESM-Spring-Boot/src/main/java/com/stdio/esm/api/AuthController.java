package com.stdio.esm.api;


import com.stdio.esm.dto.request.LoginRequestDto;
import com.stdio.esm.dto.request.RegisterRequestDto;
import com.stdio.esm.dto.response.DataResponse;
import com.stdio.esm.dto.response.RefreshTokenRequestDto;
import com.stdio.esm.service.AccountService;
import com.stdio.esm.service.SecurityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/public")
public class AuthController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "GET TOKEN")
    public DataResponse<RefreshTokenRequestDto> login(@Valid @RequestBody LoginRequestDto request) {
         securityService.login(request);
         var s = securityService.login(request);
        return DataResponse.success(s);

    }
    @PostMapping("/sign-up")
    @ApiOperation(value = "REGISTER ACCOUNT")
    public DataResponse<String> registerAccount(@Valid @RequestBody RegisterRequestDto request) {
        accountService.registerAccount(request);
        return DataResponse.success();
    }

    @PostMapping("/refresh-token")
    @ApiOperation(value = "GET NEW ACCESS TOKEN")
    public DataResponse<String> refreshToken(@Valid @RequestBody RefreshTokenRequestDto request){
        return DataResponse.success(securityService.accessTokenReisue(request));
    }
}
