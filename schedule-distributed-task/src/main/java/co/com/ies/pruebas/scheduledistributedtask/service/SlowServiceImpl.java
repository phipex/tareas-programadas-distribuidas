package co.com.ies.pruebas.scheduledistributedtask.service;

import co.com.ies.pruebas.scheduledistributedtask.persistence.Execution;
import co.com.ies.pruebas.scheduledistributedtask.persistence.ExecutionRepository;
import co.com.ies.pruebas.scheduledistributedtask.persistence.SlowView;
import co.com.ies.pruebas.scheduledistributedtask.persistence.SlowViewRepository;
import co.com.ies.pruebas.scheduledistributedtask.config.MeasureTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SlowServiceImpl implements SlowService {

    Logger logger = LoggerFactory.getLogger(SlowServiceImpl.class);

    private final SlowViewRepository slowViewRepository;

    private final ExecutionRepository executionRepository;

    public SlowServiceImpl(SlowViewRepository slowViewRepository, ExecutionRepository executionRepository) {
        this.slowViewRepository = slowViewRepository;
        this.executionRepository = executionRepository;
    }

    @Override
    public Optional<SlowView> getVistaLenta(){
        return slowViewRepository.findFirstByIdNotNull();
    }

    @Override
    public List<HighContainers> createHighContainers(){
        logger.info("SlowService.createHighContainers");
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

    @Override
    @Async
    @MeasureTime
    public void test(){
        logger.info("SlowService.test");
        queryHighContainers();
    }


    @Override
    public void queryHighContainers(Long execitionId) {
        Execution execution = executionRepository.getById(execitionId);
        String ip = "0";
        try {
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String hostName = InetAddress.getLocalHost().getHostName();
            ip = hostAddress;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        execution.setResolve(ip);
        execution.setStart(ZonedDateTime.now());
        queryHighContainers();
        execution.setFinish(ZonedDateTime.now());
        executionRepository.save(execution);
    }

    @Override
    @MeasureTime
    public List<HighContainers> queryHighContainers(){
        logger.info("SlowService.queryHighContainers");
        List<HighContainers> highContainers = createHighContainers();

        for (HighContainers highContainer : highContainers) {
            fillHighContainers(highContainer);
        }
        logger.info("SlowService.queryHighContainers");
        logger.info("highContainers = " + highContainers);
        return highContainers;
    }


    @Override
    public void fillHighContainers(HighContainers highContainer) {
        logger.info("SlowService.fillHighContainers");
        List<ListContainers> listContainers = highContainer.listContainers;
        for (ListContainers listContainer : listContainers) {
            fillListContainers(listContainer);
        }
        logger.info("SlowService.fillHighContainers");
        logger.info("listContainers = " + listContainers);
    }


    @Override
    public void fillListContainers(ListContainers listContainer) {
        logger.info("SlowService.fillListContainers");
        List<SlowView> slowViews = listContainer.slowViews;
        for (int i = 0; i < INITIAL_CAPACITY;i++) {
            Optional<SlowView> vistaLenta = getVistaLenta();
            slowViews.add(vistaLenta.get());
        }
    }

}
