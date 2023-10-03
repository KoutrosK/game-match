package gr.accepted.gamematch.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class HazelcastConfiguration {

	@Bean
	public HazelcastInstance hazelcastInstance() {

		MapConfig entitiesMap = new MapConfig()
				.setName("entity").setMaxIdleSeconds(90).setTimeToLiveSeconds(180)
				.setEvictionConfig(new EvictionConfig()
						.setEvictionPolicy(EvictionPolicy.LRU)
						.setMaxSizePolicy(MaxSizePolicy.PER_NODE).setSize(1600));

		MapConfig serviceMap = new MapConfig()
				.setName("service").setMaxIdleSeconds(180).setTimeToLiveSeconds(360)
				.setEvictionConfig(new EvictionConfig()
						.setEvictionPolicy(EvictionPolicy.LRU)
						.setMaxSizePolicy(MaxSizePolicy.PER_NODE).setSize(1600));

		return Hazelcast.newHazelcastInstance(new Config().setInstanceName("gamematch-instance")
				.addMapConfig(entitiesMap).addMapConfig(serviceMap));
	}
	
	@Bean("hazelcastKeyGenerator")
	public KeyGenerator keyGenerator() {
		
		return new HazelcastKeyGenerator();
	}

}
