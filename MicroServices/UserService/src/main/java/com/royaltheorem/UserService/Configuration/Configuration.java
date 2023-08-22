package com.royaltheorem.UserService.Configuration;

import com.royaltheorem.UserService.Service.UserService;
import com.royaltheorem.UserService.Service.UserServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public UserService userService()
    {
        return new UserServiceImplementation();
    }
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
    @Bean
    public RedisConnectionFactory redisConnectionFactory()
    {
        RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration();
        configuration.setHostName("redis-14364.c9.us-east-1-4.ec2.cloud.redislabs.com");
        configuration.setPort(14364);
        configuration.setUsername("default");
        configuration.setPassword("kXHnQH5GYZa6etUezx0jTqoo9PkT5QIV");
        return new JedisConnectionFactory(configuration);
    }
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
