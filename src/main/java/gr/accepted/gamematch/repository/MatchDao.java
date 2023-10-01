package gr.accepted.gamematch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.accepted.gamematch.model.Match;

@Repository
public interface MatchDao extends JpaRepository<Match, Long> {

}
