package com.ems.security.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ems.security.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	private RedisTemplate<String, Object> template;

	@Override
	public Object getValue(String key) {
		return template.opsForValue().get(key);

	}

	@Override
	public void setValue(String key, Object value) {
		template.opsForValue().set(key, value);
		template.expire(key, 24, TimeUnit.HOURS);

	}

	@Override
	public void deleteValue(String key) {
		Set<String> keys = new HashSet<>();
		keys.add(key);
		template.opsForValue().getOperations().delete(keys);

	}

}
