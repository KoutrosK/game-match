package gr.accepted.gamematch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.accepted.gamematch.exception.NotFoundException;
import gr.accepted.gamematch.model.Match;
import gr.accepted.gamematch.repository.MatchDao;
import gr.accepted.gamematch.service.MatchService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MatchServiceImpl implements MatchService {

	@Autowired
	private MatchDao matchDao;

	@Override
	public Match getMatchById(String matchId) {

		return matchDao.findById(Long.valueOf(matchId))
				.orElseThrow(() -> new NotFoundException("Match with id " + matchId + " does not exist"));
	}

	@Override
	public List<Match> getAllMatches() {

		return matchDao.findAll();

	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Match createMatch(@NotNull @Valid Match match) {

		return matchDao.saveAndFlush(match);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public Match updateMatch(@NotNull @Valid Match match) {

		return null;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteMatchById(@NotNull String matchId) {

		Match match = matchDao.findById(Long.valueOf(matchId))
                .orElseThrow(() -> new NotFoundException("Match with id " + matchId + " does not exist"));
		
		matchDao.delete(match);


	}

}
