package gr.accepted.gamematch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gr.accepted.gamematch.model.MatchOdd;

@Repository
public interface MatchOddDao extends JpaRepository<MatchOdd, Long> {

	Optional<MatchOdd> findByMatch(Long matchId);

}
