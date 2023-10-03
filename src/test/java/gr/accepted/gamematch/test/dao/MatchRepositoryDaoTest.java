package gr.accepted.gamematch.test.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import gr.accepted.gamematch.model.Match;
import gr.accepted.gamematch.model.MatchOdd;
import gr.accepted.gamematch.model.Sport;
import gr.accepted.gamematch.repository.MatchDao;
import lombok.extern.log4j.Log4j2;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
@DisplayName("MatchRepositoryDao test")
@Log4j2
@DataJpaTest
public class MatchRepositoryDaoTest {

	@Autowired
	private MatchDao matchDao;

	@DisplayName("Should get match by id")
	@Test
	@Tag("dao")
	public void shouldGetMatch() {

		Assertions.assertNotNull(matchDao.findById(1L).get(), "Should return one receipt");
	}

	@DisplayName("Should get matches")
	@Test
	@Tag("dao")
	public void shouldGetMatches() {

		Assertions.assertTrue(!matchDao.findAll().isEmpty());
	}

	@DisplayName("Should create match")
	@Test
	@Tag("dao")
	public void shouldCreateMatch() {

		List<MatchOdd> matchOdds = new ArrayList<>();

		MatchOdd matchOdd = new MatchOdd();
		matchOdd.setOdd(new BigDecimal(0.75));
		matchOdd.setSpecifier("X");

		Match match = new Match();

		match.setDescription("Description");
		match.setMatchDate(LocalDate.now());
		match.setMatchTime(LocalTime.now());
		match.setSport(Sport.BASKETBALL);
		match.setTeamA("AEK");
		match.setTeamB("OSFP");
		match.setMatchOdds(matchOdds);

	}

	@DisplayName("Should delete match")
	@Test
	@Tag("dao")
	public void shouldDeleteMatch() {

		matchDao.delete(matchDao.findById(2L).get());

	}

}
