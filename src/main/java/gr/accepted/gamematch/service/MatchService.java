package gr.accepted.gamematch.service;

import java.util.List;

import gr.accepted.gamematch.model.Match;

public interface MatchService {

	Match getMatchById(Long matchId);

	List<Match> getAllMatches();

	Match createMatch(Match match);

	Match updateMatch(Match match, Long matchId);

	void deleteMatchById(Long matchId);

}
