package com.jeequan.jeepay.components.mq.executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/*
* MQ 线程池配置
*
*/
@Configuration
@EnableAsync
public class MqThreadExecutor {

    public static final String EXECUTOR_PAYORDER_MCH_NOTIFY = "mqQueue4PayOrderMchNotifyExecutor";

    /*
     * 功能描述:
     * 支付结果通知到商户的异步执行器 （由于量大， 单独新建一个线程池处理， 之前的不做变动 ）
     * 20, 300, 10, 60  该配置： 同一时间最大并发量300，（已经验证通过， 商户都可以收到请求消息）
     * 缓存队列尽量减少，否则将堵塞在队列中无法执行。  corePoolSize 根据机器的配置进行添加。此处设置的为20
     */
    @Bean
    public Executor mqQueue4PayOrderMchNotifyExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20); // 线程池维护线程的最少数量
        executor.setMaxPoolSize(300);  // 线程池维护线程的最大数量
        executor.setQueueCapacity(10); // 缓存队列
        executor.setThreadNamePrefix("payOrderMchNotifyExecutor-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //对拒绝task的处理策略
        executor.setKeepAliveSeconds(60); // 允许的空闲时间
        executor.initialize();
        return executor;
    }

}
