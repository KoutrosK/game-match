package gr.accepted.gamematch.service;

import java.util.List;

import gr.accepted.gamematch.model.MatchOdd;

public interface MatchOddService {
	
	MatchOdd getMatchOddById(String matchOddId);
	
	MatchOdd getMatchOddByMatchId(String matchId);
	
	List<MatchOdd> getMatchOdds();
	
	MatchOdd createMatchOdd(MatchOdd matchOdd);
	
	MatchOdd updateMatchOdd(MatchOdd matchOdd);
	
	void deleteMatchOdd(String matchOddId);
	
	void deleteMatchOddsByMatchId(String matchId);

}
