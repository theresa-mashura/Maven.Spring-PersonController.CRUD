package io.zipcoder.crudapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest	// Tells Spring Boot to look for a main config class (one w/ @SpringBootApplication) & use that to start a context
public class CRUDApplicationTests {

	@Autowired	// controller is injected before test methods are run
	private PersonController personController;

	@Test
	public void contextLoads() throws IOException {
		Assert.assertNotNull(personController);
	}





}
