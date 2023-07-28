package co.com.ies.pruebas.scheduledistributedtask.web;

import co.com.ies.pruebas.scheduledistributedtask.config.MeasureTime;
import co.com.ies.pruebas.scheduledistributedtask.persistence.Execution;
import co.com.ies.pruebas.scheduledistributedtask.persistence.ExecutionRepository;
import co.com.ies.pruebas.scheduledistributedtask.persistence.SlowView;
import co.com.ies.pruebas.scheduledistributedtask.service.SlowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;

@RestController
public class SlowController {

    private final SlowService slowService;

    private final ExecutionRepository executionRepository;

    public SlowController(SlowService slowService, ExecutionRepository executionRepository) {
        this.slowService = slowService;
        this.executionRepository = executionRepository;
    }

    @MeasureTime
    @GetMapping("/vista")
    public ResponseEntity<SlowView> getVistaLenta() {
        return ResponseEntity.of(slowService.getVistaLenta());
    }

    @MeasureTime
    @GetMapping("/vista2")
    public ResponseEntity<String> getVistaLentaContainers() {
        slowService.test();
        return ResponseEntity.ok("VistaLentaContainers");
    }

    @MeasureTime
    @GetMapping("/vista3")
    public ResponseEntity<String> getVistaLentaContainersQeue() {
        // jobScheduler.enqueue(() -> slowService.queryHighContainers());
        String ip = "0";
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String hostName = InetAddress.getLocalHost().getHostName();
            ip = hostAddress;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Execution execution = new Execution();
        execution.setLaunch(ip);
        execution.setCalled(ZonedDateTime.now());
        Execution saved = executionRepository.save(execution);


        return ResponseEntity.ok("queryHighContainers");
    }

    @MeasureTime
    @GetMapping("/vista4")
    public ResponseEntity<String> getVistaLentaContainersSchedule() {
        // jobScheduler.enqueue(() -> slowService.queryHighContainers());
        String ip = "0";
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String hostName = InetAddress.getLocalHost().getHostName();
            ip = hostAddress;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }


        return ResponseEntity.ok("queryHighContainers");
    }
}
