package gr.accepted.gamematch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gr.accepted.gamematch.exception.NotFoundException;
import gr.accepted.gamematch.model.Match;
import gr.accepted.gamematch.model.MatchOdd;
import gr.accepted.gamematch.repository.MatchDao;
import gr.accepted.gamematch.repository.MatchOddDao;
import gr.accepted.gamematch.service.MatchOddService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MatchOddServiceImpl implements MatchOddService {

	@Autowired
	private MatchOddDao matchOddDao;

	@Autowired
	private MatchDao matchDao;

	@Override
	public MatchOdd getMatchOddById(Long matchOddId) {

		return matchOddDao.findById(matchOddId)
				.orElseThrow(() -> new NotFoundException("Match odd with id " + matchOddId + " does not exist"));

	}

	@Override
	public List<MatchOdd> getMatchOddByMatchId(Long matchId) {

		Match match = matchDao.findById(matchId)
				.orElseThrow(() -> new NotFoundException("Match with id " + matchId + " does not exist"));
		
		return match.getMatchOdds();
	}

	@Override
	@Cacheable(value = "getMatchOdds", key = "#p0", condition = "#p0!=null")
	public List<MatchOdd> getMatchOdds() {

		return matchOddDao.findAll();

	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public MatchOdd createMatchOdd(@NotNull @Valid MatchOdd matchOdd, Long matchId) {

		Match match = matchDao.findById(matchId)
				.orElseThrow(() -> new NotFoundException("Match with id " + matchId + " does not exist"));

		matchOdd.setMatch(match);

		return matchOddDao.save(matchOdd);

	}

	@Override
	public MatchOdd updateMatchOdd(@NotNull @Valid MatchOdd matchOdd, Long matchOddId, Long matchId) {

		// Check if match odd exists
		MatchOdd persistedMatchOdd = matchOddDao.findById(matchOddId)
				.orElseThrow(() -> new NotFoundException("Match odd with id " + matchOddId + " does not exist"));
		
		Match match = matchDao.findById(matchId)
				.orElseThrow(() -> new NotFoundException("Match with id " + matchId + " does not exist"));
		
		// Set the match
		matchOdd.setMatch(match);

		// Set the id of the match odd in order to update the existing record
		matchOdd.setId(persistedMatchOdd.getId());

		// Persist match odd
		return matchOddDao.save(matchOdd);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteMatchOdd(@NotNull Long matchOddId) {

		// Check if match exists
		MatchOdd matchOdd = matchOddDao.findById(matchOddId)
				.orElseThrow(() -> new NotFoundException("Match odd with id " + matchOddId + " does not exist"));

		// Delete the match
		matchOddDao.delete(matchOdd);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteMatchOddsByMatchId(@NotNull Long matchId) {

		matchOddDao.deleteByMatchId(matchId);
	}

}
