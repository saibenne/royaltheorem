package com.royaltheorem.Designer.Configuration;

import com.royaltheorem.Designer.Service.DesignerService;
import com.royaltheorem.Designer.Service.DesignerServiceImplementation;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.*;
import javax.management.ObjectName;

@org.springframework.context.annotation.Configuration
@EnableCaching
public class Configuration {
    @Bean
    public DesignerService designerService(){
        return new DesignerServiceImplementation();
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    JedisConnectionFactory jedisConnectionFactory()
    {
        RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration();
        configuration.setHostName("redis-14364.c9.us-east-1-4.ec2.cloud.redislabs.com");
        configuration.setPort(14364);
        configuration.setUsername("default");
        configuration.setPassword("kXHnQH5GYZa6etUezx0jTqoo9PkT5QIV");
        return new JedisConnectionFactory(configuration);
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory)
    {
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;


    }
}
