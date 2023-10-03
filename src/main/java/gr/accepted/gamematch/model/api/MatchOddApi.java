package gr.accepted.gamematch.model.api;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchOddApi {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "X")
	private String specifier;

	@Schema(example = "0.75")
	private BigDecimal odd;

	@Schema(example = "1")
	private Long matchId;

}
