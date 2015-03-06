package net.woniper.spring.boot.restful.example.repository;

import net.woniper.spring.boot.restful.example.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by woniper on 15. 3. 1..
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
