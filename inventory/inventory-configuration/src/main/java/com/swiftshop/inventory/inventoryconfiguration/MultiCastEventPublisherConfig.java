package com.swiftshop.inventory.inventoryconfiguration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

@Configuration
@EnableAsync
public class MultiCastEventPublisherConfig {

  private static final Integer CORE_POOL_SIZE = 50;
  private static final Integer MAX_POOL_SIZE = 200;
  private static final Long KEEP_ALIVE_TIME = 20L;
  private static final TimeUnit UNIT_TIME = TimeUnit.SECONDS;
  private static final Integer BLOCKING_QUEUE_SIZE = 200;


  @Bean
  public ApplicationEventMulticaster buildApplicationEventMulticaster() {
    SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
    ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
        KEEP_ALIVE_TIME, UNIT_TIME, new LinkedBlockingQueue<>(BLOCKING_QUEUE_SIZE));
    eventMulticaster.setTaskExecutor(executor);
    return eventMulticaster;

  }

}
