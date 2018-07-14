package com.bank.customer.statement.processor.CustomerStatementProcessor;

import java.util.logging.Logger;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class is the starting point of the application.
 * 
 * @author Tanmay Kumar Patra
 *
 */
public class AppStarter {

	private static final Logger LOGGER = Logger.getLogger(AppStarter.class.getName());

	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			LOGGER.severe("ENDING APPLICATION INSTANCE AS REQUIRED ARGUMENTS ARE NOT PROVIDED");
			throw new Exception("PLEASE PROVIDE ARGUMENTS AS REQUIRED");
		}

		//Determine the input file location and processing type
		String inputFileLocation = args[0];
		String fileType = inputFileLocation.split("\\.")[1];
		String processingType;
		if (fileType.equalsIgnoreCase("csv")) {
			processingType = "CSVreportJob";
		} else if (fileType.equalsIgnoreCase("xml")) {
			processingType = "XMLreportJob";
		} else {
			LOGGER.severe("ENDING APPLICATION INSTANCE AS INPUT FILE TYPE IS NOT XML/CSV");
			throw new Exception("PLEASE PROVIDE XML/CSV FILE TYPE");
		}
		
		//Determine the output file location
		String outputFileLocation = args[1];
		
		String[] springConfig = { "config.xml", "job-config.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean(processingType);

		JobParametersBuilder jobBuilder = new JobParametersBuilder();
		jobBuilder.addString("inputFileLocation", inputFileLocation);
		jobBuilder.addString("outputFileLocation", outputFileLocation);
		JobParameters jobParameters = jobBuilder.toJobParameters();
		JobExecution execution = jobLauncher.run(job, jobParameters);
		LOGGER.info("Exit Status : " + execution.getStatus());

		LOGGER.info("JOB EXECUTION COMPLETED !!");

	}
}
