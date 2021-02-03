package com.ambevtech;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class CaseAmbevTechApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	Flyway flyway;

	@Before
	public void init(){
		flyway.clean();
		flyway.migrate();
	}

}
