package gr.accepted.gamematch.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gr.accepted.gamematch.exception.ExceptionData;
import gr.accepted.gamematch.model.MatchOdd;
import gr.accepted.gamematch.model.api.MatchApi;
import gr.accepted.gamematch.model.api.MatchOddApi;
import gr.accepted.gamematch.service.MatchOddService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/match-odd", produces = "application/json")
@Tag(name = "Match Odds API")
public class MatchOddController {

	@Autowired
	private MatchOddService matchOddService;

	@Autowired
	private ModelMapper modelMapper;

	@Operation(summary = "Get all match odds")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = MatchOddApi.class))) })
	})
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<MatchOddApi> getAllMatchOdds() {

		return matchOddService.getMatchOdds().stream().map(match -> modelMapper.map(match, MatchOddApi.class)).toList();

	}

	@Operation(summary = "Get match odd by a given ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchOddApi.class))),
			@ApiResponse(responseCode = "404 NOT FOUND", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionData.class)))
	})
	@GetMapping("/{matchOddId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MatchOddApi getMatchOddById(
			@Parameter(description = "The Match odd id to be fetched", required = true) @PathVariable(name = "matchOddId", required = true) Long matchOddId) {

		return modelMapper.map(matchOddService.getMatchOddById(matchOddId), MatchOddApi.class);

	}

	@Operation(summary = "Get match odds by a given match ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchOddApi.class))),
			@ApiResponse(responseCode = "404 NOT FOUND", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionData.class)))
	})
	@GetMapping("/match/{matchId}")
	public List<MatchOddApi> getMatchOddsByMatchId(
			@Parameter(description = "Match's ID odds to be fetched", required = true) @PathVariable(name = "matchId", required = true) Long matchId) {

		return matchOddService.getMatchOddByMatchId(matchId).stream()
				.map(match -> modelMapper.map(match, MatchOddApi.class)).toList();

	}

	@Operation(summary = "Creates a new match odd")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchOddApi.class))),
			@ApiResponse(responseCode = "404 NOT FOUND", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionData.class)))
	})
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public MatchOddApi createMatchOdd(
			@Parameter(description = "The JSON representation of the Match odd to be created", required = true) @Valid @RequestBody(required = true) MatchOddApi matchOddApi) {

		MatchOdd matchOdd = matchOddService.createMatchOdd(modelMapper.map(matchOddApi, MatchOdd.class),
				matchOddApi.getMatchId());

		return modelMapper.map(matchOdd, MatchOddApi.class);

	}

	@Operation(summary = "Updates a match odd")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchApi.class))),
			@ApiResponse(responseCode = "404 NOT FOUND", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionData.class)))
	})
	@PutMapping("/{matchOddId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MatchOddApi updateMatchOdd(
			@Parameter(description = "The JSON representation of the Match odd to be updated", required = true) @Valid @RequestBody(required = true) MatchOddApi matchOddApi,
			@Parameter(description = "The match odd ID to be updated", required = true) @PathVariable(name = "matchOddId", required = true) Long matchOddId) {

		MatchOdd matchOdd = matchOddService.updateMatchOdd(modelMapper.map(matchOddApi, MatchOdd.class), matchOddId,
				matchOddApi.getMatchId());

		return modelMapper.map(matchOdd, MatchOddApi.class);
	}

	@Operation(summary = "Deletes a match odd by a given ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404 NOT FOUND", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionData.class)))
	})
	@DeleteMapping("/{matchOddId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteMatchOddById(
			@Parameter(description = "The match odd ID to be deleted", example = "1", required = true) @PathVariable(name = "matchOddId", required = true) Long matchOddId) {

		matchOddService.deleteMatchOdd(matchOddId);

	}

	@Operation(summary = "Deletes match odds by a given match ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation", content = @Content(mediaType = "application/json"))
	})
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteMatchOddsByMatchId(
			@Parameter(description = "The odds match id to be deleted", example = "1", required = true) @RequestParam(name = "matchId", required = true) Long matchId) {

		matchOddService.deleteMatchOddsByMatchId(matchId);

	}
}
