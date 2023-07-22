package co.com.ies.pruebas.scheduledistributedtask;

import lombok.Data;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SlowService {

    public static final int INITIAL_CAPACITY = 2;
    private final SlowViewRepository slowViewRepository;

    public SlowService(SlowViewRepository slowViewRepository) {
        this.slowViewRepository = slowViewRepository;
    }

    public Optional<SlowView> getVistaLenta(){
        return slowViewRepository.findFirstByIdNotNull();
    }

    public List<HighContainers> createHighContainers(){
        System.out.println("SlowService.createHighContainers");
        List<HighContainers> highContainers = new ArrayList<>();
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            HighContainers containers =  new HighContainers();
            highContainers.add(containers);


            for (int j = 0; j < INITIAL_CAPACITY; j++) {
                ListContainers container = new ListContainers();
                containers.listContainers.add(container);
            }

        }

        return highContainers;
    }

    @Async
    @MeasureTime
    public void test(){
        System.out.println("SlowService.test");
        queryHighContainers();
    }

    @MeasureTime
    public List<HighContainers> queryHighContainers(){
        System.out.println("SlowService.queryHighContainers");
        List<HighContainers> highContainers = createHighContainers();

        for (HighContainers highContainer : highContainers) {
            fillHighContainers(highContainer);
        }
        System.out.println("SlowService.queryHighContainers");
        System.out.println("highContainers = " + highContainers);
        return highContainers;
    }


    private void fillHighContainers(HighContainers highContainer) {
        System.out.println("SlowService.fillHighContainers");
        List<ListContainers> listContainers = highContainer.listContainers;
        for (ListContainers listContainer : listContainers) {
            fillListContainers(listContainer);
        }
        System.out.println("SlowService.fillHighContainers");
        System.out.println("listContainers = " + listContainers);
    }


    private void fillListContainers(ListContainers listContainer) {
        System.out.println("SlowService.fillListContainers");
        List<SlowView> slowViews = listContainer.slowViews;
        for (int i = 0; i < INITIAL_CAPACITY;i++) {
            Optional<SlowView> vistaLenta = getVistaLenta();
            slowViews.add(vistaLenta.get());
        }
    }

    @Data
    public static class ListContainers{
        List<SlowView> slowViews = new ArrayList<>(INITIAL_CAPACITY);
    }

    @Data
    public static class HighContainers {
        List<ListContainers> listContainers = new ArrayList<>(INITIAL_CAPACITY);
    }
}
