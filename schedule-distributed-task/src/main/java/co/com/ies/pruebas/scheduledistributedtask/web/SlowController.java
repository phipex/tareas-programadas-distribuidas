package co.com.ies.pruebas.scheduledistributedtask.web;

import co.com.ies.pruebas.scheduledistributedtask.config.MeasureTime;
import co.com.ies.pruebas.scheduledistributedtask.persistence.Execution;
import co.com.ies.pruebas.scheduledistributedtask.persistence.ExecutionRepository;
import co.com.ies.pruebas.scheduledistributedtask.persistence.SlowView;
import co.com.ies.pruebas.scheduledistributedtask.service.SlowService;

import org.redisson.api.*;
import org.redisson.api.annotation.RInject;
import org.redisson.api.executor.TaskFailureListener;
import org.redisson.api.executor.TaskFinishedListener;
import org.redisson.api.executor.TaskStartedListener;
import org.redisson.api.executor.TaskSuccessListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
public class SlowController {

    Logger logger = LoggerFactory.getLogger(SlowController.class);

    private final SlowService slowService;

    private final ExecutionRepository executionRepository;

    private final RedissonClient client;

    @Autowired
    private BeanFactory beanFactory;

    WorkerOptions options = WorkerOptions.defaults()

            // Defines workers amount used to execute tasks.
            // Default is 1.
            .workers(2)
            .executorService(Executors.newFixedThreadPool(2))
            //.beanFactory(beanFactory)

            // add listener which is invoked on task successed event
            .addListener(new TaskSuccessListener() {

                @Override
                public <T> void onSucceeded(String taskId, T result) {
                    logger.info("onSucceeded taskId {}", taskId);
                    logger.info("onSucceeded tresult {}", result);
                }
            })

            // add listener which is invoked on task failed event
            .addListener(new TaskFailureListener() {
                public void onFailed(String taskId, Throwable exception) {
                    logger.info("TaskFailureListener taskId {}", taskId);
                    logger.info("TaskFailureListener exception {}", exception);
                }
            })

            // add listener which is invoked on task started event
            .addListener(new TaskStartedListener() {
                public void onStarted(String taskId) {
                    logger.info("TaskFailureListener taskId {}", taskId);
                }
            })

            // add listener which is invoked on task finished event
            .addListener(new TaskFinishedListener() {
                public void onFinished(String taskId) {
                    logger.info("TaskFailureListener taskId {}", taskId);
                }
            });

    public SlowController(SlowService slowService, ExecutionRepository executionRepository, RedissonClient client) {
        this.slowService = slowService;
        this.executionRepository = executionRepository;
        this.client = client;
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

  /*  @MeasureTime
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
*/
/*
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
*/

    @MeasureTime
    @GetMapping("/vista5")
    public ResponseEntity<String> remote() {
        ExecutorOptions executorOptions = ExecutorOptions.defaults();
        RExecutorService executor = client.getExecutorService("myExecutor", executorOptions);
        executor.registerWorkers(options);
        callTask(executor);
        callTask(executor);
        callTask(executor);
        callTask(executor);
        callTask(executor);
        callTask(executor);
        return ResponseEntity.ok("remote");
    }

    private void callTask(RExecutorService executor) {

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

        executor.submit(new RunnableTask(saved.getId()));
    }

    private void callTask0() {

        SlowService service = client.getRemoteService().get(SlowService.class);

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

        service.queryHighContainers(saved.getId());
    }

    public static class RunnableTask implements Runnable {

    @RInject
    private RedissonClient redissonClient;

        private long param;

        public RunnableTask(long param) {
            this.param = param;
        }

        @Override
        public void run() {
            SlowService service = redissonClient.getRemoteService().get(SlowService.class);
            service.queryHighContainers(param);
        }

    }
}
