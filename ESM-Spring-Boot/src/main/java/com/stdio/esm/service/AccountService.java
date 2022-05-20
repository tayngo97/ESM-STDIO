package com.stdio.esm.service;
import com.stdio.esm.config.service.AccountDetail;
import com.stdio.esm.dto.request.ChangePasswordRequestDto;
import com.stdio.esm.dto.request.RegisterRequestDto;
import com.stdio.esm.exception.EsmException;
import com.stdio.esm.mapper.AccountMapper;
import com.stdio.esm.model.Account;
import com.stdio.esm.repository.AccountRepo;
import com.stdio.esm.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public void registerAccount(RegisterRequestDto request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new EsmException(EsmException.NOT_PASSWORD_MATCH);
        }
        accountRepo.findByUsernameIgnoreCase(request.getUsername())
                .ifPresent(tmp -> {
                    throw new EsmException(EsmException.USERNAME_EXISTS);
                });
        Account account = AccountMapper.INSTANCE.toAccount(request);

        account.setPassword(passwordEncoder.encode(account.getPassword()));

        accountRepo.save(account);
    }
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public void changePassword(ChangePasswordRequestDto request) {
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new EsmException(EsmException.NOT_PASSWORD_MATCH);
        }
        AccountDetail accountDetail = SecurityUtils.getCurrentAccountOrElseThrow();
        Account account = accountRepo.findByUsernameIgnoreCase(accountDetail.getUsername()).get();

        if (!passwordEncoder.matches(request.getOldPassword(), account.getPassword())) {
            throw new EsmException(EsmException.INCORRECT_PASSWORD);
        }
        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
    }

}
