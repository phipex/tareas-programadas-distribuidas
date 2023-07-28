package co.com.ies.pruebas.scheduledistributedtask;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ScheduleDistributedTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleDistributedTaskApplication.class, args);
	}


}
