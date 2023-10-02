package gr.accepted.gamematch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.accepted.gamematch.exception.NotFoundException;
import gr.accepted.gamematch.model.MatchOdd;
import gr.accepted.gamematch.repository.MatchOddDao;
import gr.accepted.gamematch.service.MatchOddService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MatchOddServiceImpl implements MatchOddService {

	@Autowired
	private MatchOddDao matchOddDao;

	@Override
	public MatchOdd getMatchOddById(String matchOddId) {

		return matchOddDao.findById(Long.valueOf(matchOddId))
				.orElseThrow(() -> new NotFoundException("Match odd with id " + matchOddId + " does not exist"));

	}

	@Override
	public MatchOdd getMatchOddByMatchId(String matchId) {

		return null;

	}

	@Override
	public List<MatchOdd> getMatchOdds() {

		return matchOddDao.findAll();

	}

	@Override
	public MatchOdd createMatchOdd(@NotNull @Valid MatchOdd matchOdd) {

		return matchOddDao.saveAndFlush(matchOdd);

	}

	@Override
	public MatchOdd updateMatchOdd(@NotNull @Valid MatchOdd matchOdd) {

		return null;
	}

	@Override
	public void deleteMatchOdd(@NotNull String matchOddId) {

		MatchOdd matchOdd = matchOddDao.findById(Long.valueOf(matchOddId))
				.orElseThrow(() -> new NotFoundException("Match with id " + matchOddId + " does not exist"));

		matchOddDao.delete(matchOdd);

	}

	@Override
	public void deleteMatchOddsByMatchId(@NotNull String matchId) {

		matchOddDao.deleteByMatchId(Long.valueOf(matchId));

	}

}