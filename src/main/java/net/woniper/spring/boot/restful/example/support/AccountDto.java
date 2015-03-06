package net.woniper.spring.boot.restful.example.support;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by woniper on 15. 3. 1..
 */
@Data
public class AccountDto extends ResourceSupport {

    @NotNull @Size(min = 5) private String username;
    @NotNull @Size(min = 5) private String name;
    private boolean admin;
    private boolean enable = true;

    @Data
    public static class Request extends AccountDto {
        @NotNull @Size(min = 5) private String password;
    }

    @Data
    public static class Response extends AccountDto {
        private Long accountId;
        private Date createDate;
    }
}
