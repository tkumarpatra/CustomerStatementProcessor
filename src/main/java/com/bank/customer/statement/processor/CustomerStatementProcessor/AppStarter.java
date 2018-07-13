package com.bank.customer.statement.processor.CustomerStatementProcessor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppStarter {
	  public static void main(String[] args) {

		String[] springConfig  = 
		{	
			"config.xml",
			"job-config.xml" 
		};
			
		ApplicationContext context = 
			new ClassPathXmlApplicationContext(springConfig);
			
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("CSVreportJob");

		try {

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");

	  }
	}
