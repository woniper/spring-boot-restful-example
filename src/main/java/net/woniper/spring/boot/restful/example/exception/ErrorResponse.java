package net.woniper.spring.boot.restful.example.exception;

import lombok.Data;

/**
 * Created by woniper on 15. 3. 6..
 */
@Data
public class ErrorResponse {
    private int status;
    private int code;
    private String message;
    private String developmentMessage;
    private String moreInfo;
}
