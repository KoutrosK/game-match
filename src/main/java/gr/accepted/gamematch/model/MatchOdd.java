package gr.accepted.gamematch.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "match_odds")
@Entity
public class MatchOdd {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "cannot be null")
	@Size(min = 1, max = 1, message = "max size is 1 character")
	private String specifier;

	@NotNull(message = "cannot be null")
	@Positive(message = "cannot be a negative number")
	private BigDecimal odd;

	@ManyToOne(fetch = FetchType.EAGER)
	private Match match;

}
