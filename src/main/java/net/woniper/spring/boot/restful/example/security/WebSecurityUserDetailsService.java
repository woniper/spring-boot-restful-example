package net.woniper.spring.boot.restful.example.security;

import net.woniper.spring.boot.restful.example.domain.Account;
import net.woniper.spring.boot.restful.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by woniper on 15. 3. 6..
 */
@Service
public class WebSecurityUserDetailsService implements UserDetailsService {

    @Autowired private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.getAccount(username);
        if(account == null)
            throw new UsernameNotFoundException(username);

        return new WebSecurityUserDetails(account);
    }
}
