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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import gr.accepted.gamematch.exception.ExceptionData;
import gr.accepted.gamematch.model.Match;
import gr.accepted.gamematch.model.api.MatchApi;
import gr.accepted.gamematch.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/match", produces = "application/json")
@Tag(name = "Match API")
public class MatchController {

	@Autowired
	private MatchService matchService;

	@Autowired
	private ModelMapper modelMapper;

	@Operation(summary = "Get all matches")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchApi.class)))
	})
	@GetMapping
	public List<MatchApi> getAllMatches() {

		return matchService.getAllMatches().stream().map(match -> modelMapper.map(match, MatchApi.class)).toList();

	}

	@Operation(summary = "Get match by a given ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchApi.class))),
			@ApiResponse(responseCode = "404 NOT FOUND", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionData.class)))
	})
	@GetMapping("/{matchId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MatchApi getMatchById(
			@Parameter(description = "Match ID to be fetched", example = "1", required = true) @Valid @PathVariable(name = "matchId", required = true) Long matchId) {

		return modelMapper.map(matchService.getMatchById(matchId), MatchApi.class);

	}

	@Operation(summary = "Creates a new match")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchApi.class)))
	})
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public MatchApi createNewMatch(
			@Parameter(description = "The JSON representation of the Match to be updated", required = true) @Valid @RequestBody(required = true) MatchApi matchApi) {

		return modelMapper.map(matchService.createMatch(modelMapper.map(matchApi, Match.class)), MatchApi.class);

	}

	@Operation(summary = "Updates a match")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchApi.class))),
			@ApiResponse(responseCode = "404 NOT FOUND", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionData.class)))
	})
	@PutMapping("/{matchId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MatchApi updateMatch(
			@Parameter(description = "The JSON representation of the Match to be updated", required = true) @Valid @RequestBody(required = true) MatchApi matchApi, 
			@Parameter(description = "The match ID to be updated", example = "1") @PathVariable(name = "matchId", required = true) Long matchId) {

		return modelMapper.map(matchService.updateMatch(modelMapper.map(matchApi, Match.class), matchId), MatchApi.class);

	}

	@Operation(summary = "Deletes a match by a given ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404 NOT FOUND", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionData.class)))
	})
	@DeleteMapping("/{matchId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteMatch(
			@Parameter(description = "The match ID to be deleted", example = "1", required = true) @PathVariable(name = "matchId", required = true) Long matchId) {

		matchService.deleteMatchById(matchId);

	}

}
