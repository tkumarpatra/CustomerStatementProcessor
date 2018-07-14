package com.bank.customer.statement.processor.CustomerStatementProcessor;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * We will be running end to end test test cases in this class
 * @author Tanmay Kumar Patra
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	    "classpath:config.xml",
	    "classpath:csv-job-config.xml"})
public class CustomerStatementProcessorApplicationTests_CSV{
	
	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils ;
	
	
	/**
	 * Error PATH 
	 * All input files are provided to the XML Job
	 * @throws Exception
	 */
	@Test
    public void launchJob_positive() throws Exception {
		
		JobParametersBuilder jobBuilder = new JobParametersBuilder();
		jobBuilder.addString("inputFileLocation", "classpath:record.csv");
		jobBuilder.addString("outputFileLocation", "file:ERROR_REPORT.csv");
		JobParameters jobParameters = jobBuilder.toJobParameters();
		
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
		
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		
	}
	
	/**
	 * ERROR PATH 
	 * Input file not xml
	 * @throws Exception
	 */
	@Test
    public void launchJob_negative_() throws Exception {
		
		JobParametersBuilder jobBuilder = new JobParametersBuilder();
		jobBuilder.addString("inputFileLocation", "classpath:record.xml");
		jobBuilder.addString("outputFileLocation", "file:ERROR_REPORT.csv");
		JobParameters jobParameters = jobBuilder.toJobParameters();
		
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
		
        assertEquals(BatchStatus.FAILED, jobExecution.getStatus());
		
	}
	
	/**
	 * ERROR PATH 
	 * Input file not provided
	 * @throws Exception
	 */
	@Test
    public void launchJob_negative1() throws Exception {
		
		JobParametersBuilder jobBuilder = new JobParametersBuilder();
		jobBuilder.addString("outputFileLocation", "file:ERROR_REPORT.csv");
		JobParameters jobParameters = jobBuilder.toJobParameters();
		
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
		
        assertEquals(BatchStatus.FAILED, jobExecution.getStatus());
		
	}
	
	/**
	 * ERROR PATH 
	 * Output file not provided
	 * @throws Exception
	 */
	@Test
    public void launchJob_negative2() throws Exception {
		
		JobParametersBuilder jobBuilder = new JobParametersBuilder();
		jobBuilder.addString("inputFileLocation", "classpath:record.csv");
		JobParameters jobParameters = jobBuilder.toJobParameters();
		
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
		
        assertEquals(BatchStatus.FAILED, jobExecution.getStatus());
		
	}
	
	/**
	 * ERROR PATH 
	 * Input and Output file not provided
	 * @throws Exception
	 */
	@Test
    public void launchJob_negative3() throws Exception {
		
		JobParametersBuilder jobBuilder = new JobParametersBuilder();
		JobParameters jobParameters = jobBuilder.toJobParameters();
		
		JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
		
        assertEquals(BatchStatus.FAILED, jobExecution.getStatus());
		
	}

	
}
