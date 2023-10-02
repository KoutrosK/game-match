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
		
		return matchOddDao.findByMatch(Long.valueOf(matchId))
				.orElseThrow(() -> new NotFoundException("Match odds for match with " + matchId + " not found")); 

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
	public MatchOdd updateMatchOdd(MatchOdd matchOdd) {

		return null;
	}

	@Override
	public void deleteMatchOdd(MatchOdd matchOdd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMatchOddsByMatchId(String matchId) {
		// TODO Auto-generated method stub

	}

}
