package com.example.scheduling.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /* 1. fixedRate
     * fixedRate нь тогтмол интервал.
     * 2 секунд тутамд гүйцэтгэх болно.
     * */
    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    /*
     * 2. fixed Delay
     * fixedDelay параметрийг ашиглан сүүлчийн task - ийг дуусгах болон дараагийн task - ийг
     * эхлүүлэх хооронд тогтмол саатал бүхий даалгаврыг гүйцэтгэж болно.
     * The fixedDelay parameter counts the delay after the completion of the last invocation.
     * */
    @Scheduled(fixedDelay = 2000)
    public void scheduleTaskWithFixedDelay() {
        logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            logger.error("Ran into an error {}", ex);
            throw new IllegalStateException(ex);
        }
    }
    /*
     * Даалгавар өөрөө дуусахад 5 секунд шаардагдах бөгөөд бид сүүлчийн дуудлагыг дуусгахаас
     * дараагийн дуудлагыг эхлүүлэх хооронд 2 секундын саатал тогтоосон тул дуудлага бүрийн
     * хооронд 7 секундын саатал гарна
     * */

    /*
     * 3. fixedRate and Initial Delay
     *
     * Даалгаврын эхний гүйцэтгэлийг заасан миллисекундээр хойшлуулахын тулд
     * fixedRate болон fixedDelay-тэй initialDelay параметрийг ашиглаж болно.
     *
     * Дараах жишээнд даалгаврын эхний гүйцэтгэл 5 секундээр хойшлогдож,
     * дараа нь 2 секундын тогтмол интервалаар хэвийн ажиллана -
     * */
    @Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {
        logger.info("Fixed Rate Task with Initial Delay :: Execution Time -{}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    /*
     * 4. Cron Expression
     * If the above simple parameters can not fulfill your needs, then
     * you can use cron expressions to schedule the execution of your tasks.
     * */
    @Scheduled(cron = "0 * * * * ?")
    public void scheduleTaskWithCronExpression() {
        logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
    }
}
