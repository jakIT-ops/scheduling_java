package com.example.scheduling.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Component
public class HelloWorldJob {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/*
	 * Seconds/Minutes/Hours/Day Of Month/Month/DayOfWeek/Year/
	 *	"0	   0       ?	*	*	*"
	 * */

	// 10 Секунд тутам
	@Scheduled(cron = "0/10 * * ? * *")
	public void helloWorldJob() throws Exception{
		logger.info("Job HELLO_WORLD starting @ {}", dateTimeFormatter.format(LocalDateTime.now()));;
		System.out.println("Hello World! ");
		logger.info("Job HELLO_WORLD completed. Next job scheduled @ {}", dateTimeFormatter.format(LocalDateTime.now()));
	}

   // 1 Минут тутам
	@Scheduled(cron = " 0 * * ? * *")
	public void bigHelloWorldJob() throws Exception {
		logger.info("Job BIG_HELLO_WORLD starting @ {}", dateTimeFormatter.format(LocalDateTime.now()));;
		System.out.println("HELLO WORLD! ");
		logger.info("Job BIG_HELLO_WORLD completed. Next job scheduled @ {}", dateTimeFormatter.format(LocalDateTime.now()));
	}
}
