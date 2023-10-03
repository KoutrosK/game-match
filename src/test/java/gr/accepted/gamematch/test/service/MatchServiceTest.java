package gr.accepted.gamematch.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import gr.accepted.gamematch.GameMatchApplication;
import gr.accepted.gamematch.service.MatchService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
@SpringBootTest(classes = GameMatchApplication.class)
@DisplayName("MatchService test")
@Log4j2
@Transactional
public class MatchServiceTest {

	@Autowired
	private MatchService matchService;

	@Test
	@Tag("service")
	@DisplayName("Should get matches")
	void getMatches() {

		Assertions.assertNotNull(matchService.getAllMatches());
	}

	@Test
	@Tag("service")
	@DisplayName("Should get match by ID")
	void getMatchById() {

		Assertions.assertNotNull(matchService.getMatchById(1L));
	}

}
