package net.woniper.spring.boot.restful.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.woniper.spring.boot.restful.example.domain.Account;
import net.woniper.spring.boot.restful.example.repository.AccountRepository;
import net.woniper.spring.boot.restful.example.support.AccountDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class AccountControllerTest {

	@Autowired private AccountRepository accountRepository;
	@Autowired private ObjectMapper objectMapper;
	@Autowired private ModelMapper modelMapper;
	@Autowired private WebApplicationContext wac;

	private MockMvc mockMvc;
	private Account account;
	private MediaType mediaType = MediaType.APPLICATION_JSON;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

		account = new Account();
		account.setUsername("woniper");
		account.setPassword("12345");
		account.setName("woniper");
	}

	@Test
	public void testNewAccount() throws Exception {
		// given
		AccountDto.Request accountDto = modelMapper.map(account, AccountDto.Request.class);

		// when
		ResultActions result = mockMvc.perform(post("/accounts")
				.contentType(mediaType)
				.content(objectMapper.writeValueAsBytes(accountDto)));

		// then
		result.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.username", is(accountDto.getUsername())))
				.andExpect(jsonPath("$.password", is(accountDto.getPassword())))
				.andExpect(jsonPath("$.name", is(accountDto.getName())));
	}

	@Test
	public void testAutowired() throws Exception {
		assertNotNull(accountRepository);
		assertNotNull(objectMapper);
		assertNotNull(modelMapper);
		assertNotNull(wac);
	}
}
