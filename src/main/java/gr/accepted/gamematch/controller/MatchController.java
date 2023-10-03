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

import gr.accepted.gamematch.model.Match;
import gr.accepted.gamematch.model.api.MatchApi;
import gr.accepted.gamematch.service.MatchService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/match", produces = "application/json")
public class MatchController {

	@Autowired
	private MatchService matchService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<MatchApi> getAllMatches() {

		return matchService.getAllMatches().stream().map(match -> modelMapper.map(match, MatchApi.class)).toList();

	}

	@GetMapping("/{matchId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MatchApi getMatchById(@Valid @PathVariable(name = "matchId") Long matchId) {

		return modelMapper.map(matchService.getMatchById(matchId), MatchApi.class);

	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public MatchApi createNewMatch(@Valid @RequestBody MatchApi matchApi) {

		return modelMapper.map(matchService.createMatch(modelMapper.map(matchApi, Match.class)), MatchApi.class);

	}

	@PutMapping("/{matchId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MatchApi updateMatch(@Valid @RequestBody MatchApi matchApi, @PathVariable(name = "matchId") Long matchId) {

		return modelMapper.map(matchService.updateMatch(modelMapper.map(matchApi, Match.class), matchId), MatchApi.class);

	}

	@DeleteMapping("/{matchId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteMatch(@PathVariable(name = "matchId") Long matchId) {

		matchService.deleteMatchById(matchId);

	}

}
