package com.stdio.esm.mapper;


import com.stdio.esm.config.service.AccountDetail;
import com.stdio.esm.dto.request.RegisterRequestDto;
import com.stdio.esm.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;
/**
 * @author AnhKhoa
 * @since 19/05/2022 - 11:11
 */
@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mappings({
            @Mapping(source = "authorityList", target = "authorities")
    })
    AccountDetail toAccountDetail(Account account, List<GrantedAuthority> authorityList);

    Account toAccount(RegisterRequestDto registerRequestDto);

}
