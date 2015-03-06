package net.woniper.spring.boot.restful.example.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by woniper on 15. 3. 1..
 */
@Entity(name = "account")
@Data
public class Account {

    @Id @GeneratedValue
    private Long accountId;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String name;

    private boolean admin;

    private boolean enable = true;

    @Temporal(TemporalType.DATE)
    private Date createDate = new Date();

}
