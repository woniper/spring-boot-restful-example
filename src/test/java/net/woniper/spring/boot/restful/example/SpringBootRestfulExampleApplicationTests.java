package net.woniper.spring.boot.restful.example;

import net.woniper.spring.boot.restful.example.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SpringBootRestfulExampleApplicationTests {

	@Autowired private AccountRepository accountRepository;

	@Test
	public void testName() throws Exception {
		assertNotNull(accountRepository);
	}
}
