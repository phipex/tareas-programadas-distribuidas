package co.com.ies.pruebas.scheduledistributedtask.service;

import co.com.ies.pruebas.scheduledistributedtask.config.MeasureTime;
import co.com.ies.pruebas.scheduledistributedtask.persistence.SlowView;
import lombok.Data;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface SlowService {
    int INITIAL_CAPACITY = 2;

    Optional<SlowView> getVistaLenta();

    List<HighContainers> createHighContainers();

    @Async
    @MeasureTime
    void test();

    void queryHighContainers(Long execitionId);

    @MeasureTime
    List<HighContainers> queryHighContainers();

    void fillHighContainers(HighContainers highContainer);

    void fillListContainers(ListContainers listContainer);

    @Data
    public static class ListContainers {
        List<SlowView> slowViews = new ArrayList<>(INITIAL_CAPACITY);
    }

    @Data
    public static class HighContainers {
        List<ListContainers> listContainers = new ArrayList<>(INITIAL_CAPACITY);
    }
}
