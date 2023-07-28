package co.com.ies.pruebas.scheduledistributedtask.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class SpringAsyncConfig {

    @Bean
    public RedissonClient getClient(){
        return  Redisson.create();
    }



}
