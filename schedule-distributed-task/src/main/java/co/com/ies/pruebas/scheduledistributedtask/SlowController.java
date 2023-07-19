package co.com.ies.pruebas.scheduledistributedtask;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlowController {

    private final VistaLentaRepository lentaRepository;

    public SlowController(VistaLentaRepository lentaRepository) {
        this.lentaRepository = lentaRepository;
    }

    @GetMapping("/vista")
    public ResponseEntity<VistaLenta> getVistaLenta(){
        return ResponseEntity.of(lentaRepository.findFirstByIdNotNull());
    }
}
