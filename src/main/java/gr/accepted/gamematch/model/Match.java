package gr.accepted.gamematch.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Cacheable
@Cache(region = "entity", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Match {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@EqualsAndHashCode.Exclude
	@NotNull(message = "cannot be null")
	@Size(max = 255, message = "max size is 255 characters")
	private String description;

	@EqualsAndHashCode.Include
	@Column(name = "match_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@NotNull(message = "cannot be null")
	private LocalDate matchDate;

	@EqualsAndHashCode.Include
	@Column(name = "match_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@NotNull(message = "cannot be null")
	private LocalTime matchTime;

	@EqualsAndHashCode.Include
	@Column(name = "team_a")
	@NotNull(message = "cannot be null")
	@Size(max = 255, message = "max size is 255 characters")
	private String teamA;

	@Column(name = "team_b")
	@EqualsAndHashCode.Include
	@NotNull(message = "cannot be null")
	@Size(max = 255, message = "max size is 255 characters")
	private String teamB;

	@EqualsAndHashCode.Include
	@NotNull(message = "cannot be null")
	@Convert(converter = SportConverter.class)
	private Sport sport;

	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "match", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private List<MatchOdd> matchOdds;
}
