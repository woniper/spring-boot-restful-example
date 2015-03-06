package net.woniper.spring.boot.restful.example.controller;

import net.woniper.spring.boot.restful.example.domain.Account;
import net.woniper.spring.boot.restful.example.exception.support.DuplicationUsernameException;
import net.woniper.spring.boot.restful.example.service.AccountService;
import net.woniper.spring.boot.restful.example.support.AccountDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by woniper on 15. 3. 1..
 */
@RestController
public class AccountController {

    @Autowired private AccountService accountService;
    @Autowired private ModelMapper modelMapper;

    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccount(@PathVariable("accountId") Long accountId) {
        Account account = accountService.getAccount(accountId);
        return new ResponseEntity<>(modelMapper.map(account, AccountDto.Response.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity<?> getAccount(Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        return new ResponseEntity<>(modelMapper.map(account, AccountDto.Response.class), HttpStatus.OK);
    }


    @RequestMapping(value = "/accounts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> newAccount(@RequestBody @Valid AccountDto.Request accountRequest, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<> (bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        if(accountService.isDuplicationUsername(accountRequest.getUsername())) {
            throw new DuplicationUsernameException(accountRequest.getUsername());
        }

        Account account = accountService.newAccount(accountRequest);
        return new ResponseEntity<>(modelMapper.map(account, AccountDto.Response.class), HttpStatus.CREATED);
    }

}
