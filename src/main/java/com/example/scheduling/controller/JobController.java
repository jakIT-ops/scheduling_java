package com.example.scheduling.controller;

import com.example.scheduling.scheduler.CountingJob;
import com.example.scheduling.scheduler.HelloWorldJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Set;

@RestController
@RequestMapping("/scheduler")
public class JobController {

	// tasks
	private static final String HELLO_WORLD_JOB = "helloWorldJob";
	private static final String BIG_HELLO_WORLD_JOB = "bigHelloWorldJob";
	private static final String COUNTING_JOB = "countingAsc";
	private static final String COUNTING_JOB_DESC = "countingDesc";

	@Autowired
	private ScheduledAnnotationBeanPostProcessor postProcessor;
	// job class
	@Autowired
	private HelloWorldJob helloWorldJob;

	@Autowired
	private CountingJob countingJob;

	@GetMapping("/stop/{jobName}")
	public String stopJob(@PathVariable String jobName) {
		System.out.println("Received: " + jobName);
//		postProcessor.postProcessBeforeDestruction(helloWorldJob, SCHEDULED_TASKS);
			switch (jobName) {
				case HELLO_WORLD_JOB:
					postProcessor.postProcessBeforeDestruction(helloWorldJob, HELLO_WORLD_JOB);
					break;
				case BIG_HELLO_WORLD_JOB:
					postProcessor.postProcessBeforeDestruction(helloWorldJob, BIG_HELLO_WORLD_JOB);
					break;
				case COUNTING_JOB:
					postProcessor.postProcessBeforeDestruction(countingJob, COUNTING_JOB);
					break;
				case COUNTING_JOB_DESC:
					postProcessor.postProcessBeforeDestruction(countingJob, COUNTING_JOB_DESC);
					break;
				default:
					return "Амжилтгүй боллоо";
			}

		return "OK";
	}

	@GetMapping("/start/{jobName}")
	public String startJob(@PathVariable String jobName) {
		System.out.println("Received: " + jobName);
//		postProcessor.postProcessAfterInitialization(helloWorldJob, SCHEDULED_TASKS);
			switch (jobName) {
				case HELLO_WORLD_JOB:
					postProcessor.postProcessAfterInitialization(helloWorldJob, HELLO_WORLD_JOB);
					break;
				case BIG_HELLO_WORLD_JOB:
					postProcessor.postProcessAfterInitialization(helloWorldJob, BIG_HELLO_WORLD_JOB);
					break;
				case COUNTING_JOB:
					postProcessor.postProcessAfterInitialization(countingJob, COUNTING_JOB);
					break;
				case COUNTING_JOB_DESC:
					postProcessor.postProcessAfterInitialization(countingJob, COUNTING_JOB_DESC);
					break;
				default:
					return "Амжилтгүй боллоо";
			}
		return "OK";
	}

	@GetMapping(value = "/list")
	public String listSchedules() throws Exception {

		Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
		int order = postProcessor.getOrder();
		Class<? extends ScheduledAnnotationBeanPostProcessor> clas = postProcessor.getClass();
		int count = postProcessor.getScheduledTasks().size();

		if (!setTasks.isEmpty()) {
			return setTasks.toString() + "\nИдэвхтэй job-uud: " +  count;
		} else {
			return "Идэвхтэй ажиллаж байга Job алга байна.";
		}
//		HashMap response = new HashMap();
//		if (!setTasks.isEmpty()) {
//			response.put("Нийт идэвхтэй job", count);
//			response.put("Job- ууд", setTasks.toArray());
//			return response;
//		} else {
//			response.put("Status", "Идэвхтэй ажиллаж байга Job алга байна.");
//			return response;
//		}
	}
}