package com.stdio.esm.service;
import com.stdio.esm.config.service.AccountDetail;
import com.stdio.esm.dto.request.ChangePasswordRequestDto;
import com.stdio.esm.dto.request.RegisterRequestDto;
import com.stdio.esm.exception.EsmException;
import com.stdio.esm.mapper.AccountMapper;
import com.stdio.esm.model.Account;
import com.stdio.esm.repository.AccountRepo;
import com.stdio.esm.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger LOGGER= LoggerFactory.getLogger(AccountService.class);
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public void registerAccount(RegisterRequestDto request) {
        LOGGER.info("{} registers new account",request.getUsername());
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            LOGGER.trace("new password: {} and new confirm password: {} not correct",request.getPassword(),request.getConfirmPassword());
            throw new EsmException(EsmException.NOT_PASSWORD_MATCH);
        }
        accountRepo.findByUsernameIgnoreCase(request.getUsername())
                .ifPresent(tmp -> {
                    LOGGER.trace("{} already exits in database",request.getUsername());
                    throw new EsmException(EsmException.USERNAME_EXISTS);
                });
        Account account = AccountMapper.INSTANCE.toAccount(request);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        LOGGER.info("{} registers successfully",request.getUsername());
        accountRepo.save(account);
    }
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public void changePassword(ChangePasswordRequestDto request) {
        LOGGER.info("Change password");
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            LOGGER.trace("new password and new confirmpassword not correct");
            throw new EsmException(EsmException.NOT_PASSWORD_MATCH);
        }
        AccountDetail accountDetail = SecurityUtils.getCurrentAccountOrElseThrow();
        Account account = accountRepo.findByUsernameIgnoreCase(accountDetail.getUsername()).get();
            LOGGER.info("{} change password",account.getUsername());
        if (!passwordEncoder.matches(request.getOldPassword(), account.getPassword())) {
            throw new EsmException(EsmException.INCORRECT_PASSWORD);
        }
        LOGGER.info("change password successfully");
        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
    }

}
