package gr.accepted.gamematch.model.api;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchOddApi {

	private Long id;

	private String specifier;

	private BigDecimal odd;

	private Long matchId;

}
