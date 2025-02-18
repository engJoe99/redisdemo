package com.boghdady.redisdemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisCacheConfig {

    /**
     * Creates and configures a Redis connection factory using Jedis
     * @return JedisConnectionFactory configured for localhost:6379
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        // Configure Redis to connect to local instance on default port
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration("localhost", 6379);
        return new JedisConnectionFactory(redisConfig);
    }

    /**
     * Creates and configures a RedisTemplate for key-value operations
     * @return RedisTemplate configured with String keys and JSON serialized values
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // Set the Redis connection factory
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        // Configure serializers
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class)); // Use JSON serialization
        return redisTemplate;
    }




}
