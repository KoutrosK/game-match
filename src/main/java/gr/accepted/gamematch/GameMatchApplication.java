package gr.accepted.gamematch;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import gr.accepted.gamematch.model.MatchOdd;
import gr.accepted.gamematch.model.api.MatchOddApi;

@SpringBootApplication(exclude = HazelcastAutoConfiguration.class)
@EnableCaching
public class GameMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameMatchApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		TypeMap<MatchOdd, MatchOddApi> propertyMapper = modelMapper.createTypeMap(MatchOdd.class, MatchOddApi.class);

		propertyMapper.addMappings(mapper -> mapper.map(src -> src.getMatch().getId(), MatchOddApi::setMatchId));

		return modelMapper;
	}

}
