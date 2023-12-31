package gr.accepted.gamematch.test.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import gr.accepted.gamematch.GameMatchApplication;
import gr.accepted.gamematch.service.MatchOddService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
@SpringBootTest(classes = GameMatchApplication.class)
@DisplayName("MatchOddService test")
@Log4j2
@Transactional
public class MatchOddServiceTest {

	@Autowired
	private MatchOddService matchOddService;

	@Test
	@Tag("service")
	@DisplayName("Should delete match odds by match ID")
	void getMatches() {

		matchOddService.deleteMatchOddsByMatchId(1L);

	}

}
