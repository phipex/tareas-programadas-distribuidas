package co.com.ies.pruebas.scheduledistributedtask;


import co.com.ies.pruebas.scheduledistributedtask.service.SlowService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ScheduleDistributedTaskApplication {

	@Autowired
	private SlowService slowService;

	@Autowired
	private RedissonClient client;

	public static void main(String[] args) {
		SpringApplication.run(ScheduleDistributedTaskApplication.class, args);
	}

	@PostConstruct
	public void scheduleRecurrently() {
		client.getRemoteService().register(SlowService.class, slowService);

	}

}
