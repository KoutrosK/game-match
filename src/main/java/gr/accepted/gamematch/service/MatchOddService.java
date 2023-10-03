package gr.accepted.gamematch.service;

import java.util.List;

import gr.accepted.gamematch.model.MatchOdd;

public interface MatchOddService {

	MatchOdd getMatchOddById(Long matchOddId);

	List<MatchOdd> getMatchOddByMatchId(Long matchId);

	List<MatchOdd> getMatchOdds();

	MatchOdd createMatchOdd(MatchOdd matchOdd, Long matchId);

	MatchOdd updateMatchOdd(MatchOdd matchOdd, Long matchOddId, Long matchId);

	void deleteMatchOdd(Long matchOddId);

	void deleteMatchOddsByMatchId(Long matchId);

}
