package co.com.ies.pruebas.scheduledistributedtask;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlowController {

    private final SlowService slowService;

    public SlowController(SlowService slowService) {
        this.slowService = slowService;
    }

    @MeasureTime
    @GetMapping("/vista")
    public ResponseEntity<SlowView> getVistaLenta(){
        return ResponseEntity.of(slowService.getVistaLenta());
    }

    @MeasureTime
    @GetMapping("/vista2")
    public ResponseEntity<String> getVistaLentaContainers(){
        slowService.test();
        return ResponseEntity.ok("VistaLentaContainers");
    }
}
