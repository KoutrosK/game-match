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

import gr.accepted.gamematch.model.MatchOdd;
import gr.accepted.gamematch.model.api.MatchOddApi;
import gr.accepted.gamematch.service.MatchOddService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/match-odd", produces = "application/json")
public class MatchOddController {

	@Autowired
	private MatchOddService matchOddService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<MatchOddApi> getAllMatchOdds() {

		return matchOddService.getMatchOdds().stream().map(match -> modelMapper.map(match, MatchOddApi.class)).toList();

	}

	@GetMapping("/{matchOddId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MatchOddApi getMatchOddById(@PathVariable Long matchOddId) {

		return modelMapper.map(matchOddService.getMatchOddById(matchOddId), MatchOddApi.class);

	}
	
	@GetMapping("/match/{matchId}")
	public List<MatchOddApi> getMatchOddsByMatchId(@PathVariable("matchId") Long matchId) {
		
		return matchOddService.getMatchOddByMatchId(matchId).stream().map(match -> modelMapper.map(match, MatchOddApi.class)).toList();

	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public MatchOddApi createMatchOdd(@Valid @RequestBody MatchOddApi matchOddApi) {
		
		MatchOdd matchOdd = matchOddService.createMatchOdd(modelMapper.map(matchOddApi, MatchOdd.class), matchOddApi.getMatchId());
		
		return modelMapper.map(matchOdd, MatchOddApi.class);

	}
	
	@PutMapping("/{matchOddId}")
	@ResponseStatus(code = HttpStatus.OK)
	public MatchOddApi updateMatchOdd(@Valid @RequestBody MatchOddApi matchOddApi, @PathVariable(name = "matchOddId") Long matchOddId) {
		
		MatchOdd matchOdd = matchOddService.updateMatchOdd(modelMapper.map(matchOddApi, MatchOdd.class), matchOddId, matchOddApi.getMatchId());
		
		return modelMapper.map(matchOdd, MatchOddApi.class);
	}

	@DeleteMapping("/{matchOddId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteMatchOddById(@PathVariable Long matchOddId) {

		matchOddService.deleteMatchOdd(matchOddId);

	}

	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteMatchOddsByMatchId(@RequestParam("matchId") Long matchId) {

		matchOddService.deleteMatchOddsByMatchId(matchId);

	}
}
