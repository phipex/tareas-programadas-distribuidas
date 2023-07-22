package co.com.ies.pruebas.scheduledistributedtask;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SlowService {

    private final SlowViewRepository slowViewRepository;

    public SlowService(SlowViewRepository slowViewRepository) {
        this.slowViewRepository = slowViewRepository;
    }

    public Optional<SlowView> getVistaLenta(){
        return slowViewRepository.findFirstByIdNotNull();
    }

}
