package gr.accepted.gamematch.model.api;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import gr.accepted.gamematch.model.MatchOdd;
import gr.accepted.gamematch.model.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchApi {

	@Schema(example = "1")
	private Long id;

	@Schema(example = "Description")
	private String description;

	@Schema(example = "31/03/2021")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate matchDate;

	@Schema(example = "15:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@JsonSerialize(using = LocalTimeSerializer.class)
	private LocalTime matchTime;

	@Schema(example = "AEK")
	private String teamA;

	@Schema(example = "OSFP")
	private String teamB;

	@Schema(example = "FOOTBALL")
	private Sport sport;

	private List<MatchOdd> matchOdds;
}
