package co.com.ies.pruebas.scheduledistributedtask;

import co.com.ies.pruebas.scheduledistributedtask.service.SampleJobService;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ScheduleDistributedTaskApplication {

	@Autowired
	private JobScheduler jobScheduler;

	public static void main(String[] args) {
		SpringApplication.run(ScheduleDistributedTaskApplication.class, args);
	}

	@PostConstruct
	public void scheduleRecurrently() {
		jobScheduler.<SampleJobService>scheduleRecurrently(Cron.every5minutes(), x -> x.executeSampleJob("a recurring job"));
	}

}
