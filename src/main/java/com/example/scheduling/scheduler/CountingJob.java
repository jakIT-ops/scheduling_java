package com.example.scheduling.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@Component
public class CountingJob {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /*
    * Seconds/Minutes/Hours/Day Of Month/Month/DayOfWeek/Year/
     *	"0	   0       ?	*	*	*"
    * */
    // 30 секунд тутам
    @Scheduled(cron = "0/30 * * ? * *")
    public void countingAsc() throws Exception {
        logger.info("Job COUNTING starting @{}", dateTimeFormatter.format(LocalDateTime.now()));
        IntStream.range(0, 10).forEach(i ->{
            logger.info("Counting - {}", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        });
        logger.info("Job COUNTING completed. Next job scheduled @ {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

    // 40 секунд тутам
    @Scheduled(cron = "0/40 * * ? * *")
    public void countingDesc() throws Exception {
        logger.info("Job COUNTING starting @{}", dateTimeFormatter.format(LocalDateTime.now()));
        IntStream.range(10, 0).forEach(i ->{
            logger.info("Counting - {}", i);
            try {
                // 1 sec sleep hiine
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        });
        logger.info("Job COUNTING completed. Next job scheduled @ {}", dateTimeFormatter.format(LocalDateTime.now()));
    }


}
