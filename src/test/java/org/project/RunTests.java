package org.project;

import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.project.entity.WinnerJson;
import org.project.service.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RunTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private WinnerService winnerService;

	@Test
	public void findWinners() {

		ResponseEntity<WinnerJson> responseEntity = this.testRestTemplate
				.exchange("/winner/result", HttpMethod.GET, null, WinnerJson.class);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

}
