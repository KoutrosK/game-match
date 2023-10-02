package gr.accepted.gamematch.service;

import java.util.List;

import gr.accepted.gamematch.model.Match;

public interface MatchService {

	Match getMatchById(String matchId);

	List<Match> getAllMatches();

	Match createMatch(Match match);

	Match updateMatch(Match match);

	void deleteMatchById(String matchId);

}
