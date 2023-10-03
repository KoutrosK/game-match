package gr.accepted.gamematch.config;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.StringUtils;

public class HazelcastKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {

		return target.getClass().getSimpleName() + "_" + method.getName() + "_" + StringUtils.arrayToDelimitedString(params, "_");
	}
}
