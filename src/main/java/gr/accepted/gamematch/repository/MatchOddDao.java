package gr.accepted.gamematch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gr.accepted.gamematch.model.MatchOdd;

@Repository
public interface MatchOddDao extends JpaRepository<MatchOdd, Long> {

	Optional<List<MatchOdd>> findByMatchId(Long matchId);

	@Modifying
	@Query("delete from MatchOdd m where m.match.id =:matchId")
	void deleteByMatchId(@Param("matchId") Long matchId);

}
