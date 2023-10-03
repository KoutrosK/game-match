package gr.accepted.gamematch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gr.accepted.gamematch.exception.NotFoundException;
import gr.accepted.gamematch.model.Match;
import gr.accepted.gamematch.repository.MatchDao;
import gr.accepted.gamematch.repository.MatchOddDao;
import gr.accepted.gamematch.service.MatchOddService;
import gr.accepted.gamematch.service.MatchService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;

@Service
@CacheConfig(cacheNames = { "service" })
@Log4j2
public class MatchServiceImpl implements MatchService {

	@Autowired
	private MatchDao matchDao;

	@Autowired
	private MatchOddDao matchOddDao;

	@Override
	public Match getMatchById(Long matchId) {

		return matchDao.findById(matchId)
				.orElseThrow(() -> new NotFoundException("Match with id " + matchId + " does not exist"));
	}

	@Override
	@Cacheable(value = "getAllMatches", key = "#p0", condition = "#p0!=null")
	public List<Match> getAllMatches() {

		return matchDao.findAll();

	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	@CacheEvict(allEntries = true)
	public Match createMatch(@NotNull @Valid Match match) {

		// TODO: Change location
		match.getMatchOdds().stream().forEach((matchOdd) -> matchOdd.setMatch(match));

		// Save the match to DB
		return matchDao.save(match);

	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	@CacheEvict(allEntries = true)
	public Match updateMatch(@NotNull @Valid Match match, @NotNull Long matchId) {

		Match persistedMatch = matchDao.findById(matchId)
				.orElseThrow(() -> new NotFoundException("Match with id " + matchId + " does not exist"));

		// Set the id of the match in order to update the existing record
		match.setId(persistedMatch.getId());

		// Means there are match odds to update the match with
		if (match.getMatchOdds() != null && !match.getMatchOdds().isEmpty()) {

			// Delete the previous odds
			matchOddDao.deleteByMatchId(matchId);

			// Set the current odds to match
			match.getMatchOdds().stream().forEach(matchOdd -> matchOdd.setMatch(match));
		}

		// Save the match to DB
		return matchDao.save(match);

	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	@CacheEvict(allEntries = true)
	public void deleteMatchById(@NotNull Long matchId) {

		// Check if match exists
		Match match = matchDao.findById(matchId)
				.orElseThrow(() -> new NotFoundException("Match with id " + matchId + " does not exist"));

		// Delete the match
		matchDao.delete(match);

	}

}
