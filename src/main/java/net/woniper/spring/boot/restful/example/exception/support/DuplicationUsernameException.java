package net.woniper.spring.boot.restful.example.exception.support;

import lombok.Getter;

/**
 * Created by woniper on 15. 3. 6..
 */
public class DuplicationUsernameException extends RuntimeException {

    @Getter
    private String username;

    public DuplicationUsernameException(String username) {
        this.username = username;
    }

}
