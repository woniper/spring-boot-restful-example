package net.woniper.spring.boot.restful.example.service;

import net.woniper.spring.boot.restful.example.domain.Account;
import net.woniper.spring.boot.restful.example.repository.AccountRepository;
import net.woniper.spring.boot.restful.example.support.AccountDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by woniper on 15. 3. 1..
 */
@Service
public class AccountService {

    @Autowired private AccountRepository accountRepository;
    @Autowired private ModelMapper modelMapper;

    public Account newAccount(AccountDto.Request accountRequest) {
        Account newAccount = modelMapper.map(accountRequest, Account.class);
        return accountRepository.save(newAccount);
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findOne(accountId);
    }
}
