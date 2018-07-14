package com.bank.customer.statement.processor.CustomerStatementProcessor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bank.customer.statement.processor.CustomerStatementProcessor.model.ReportModel;
import com.bank.customer.statement.processor.CustomerStatementProcessor.processor.BusinessProcessor;
import com.bank.customer.statement.processor.CustomerStatementProcessor.utils.ApplicationCommonUtility;

/**
 * This class tests the business methods
 * @author Tanmay Kumar Patra
 *
 */

public class BusinessProcessorTest {
	
	ApplicationCommonUtility aApplicationCommonUtility;
	BusinessProcessor aBusinessProcessor;
	ReportModel aReportModel;
	
	@Before
    public void runBeforeTestMethod() {
		aApplicationCommonUtility = new ApplicationCommonUtility();
		aBusinessProcessor =  new BusinessProcessor();
		aReportModel = new ReportModel();

    }
	
	@After
    public void runAfterTestMethod() {
		aApplicationCommonUtility = null;
		aBusinessProcessor = null;
		aReportModel = null;
		aBusinessProcessor.transactionReferenceNumber.clear();
    }
	
	/**
	 * Testing positive case for ApplicationCommonUtility.isDuplicateTranactionRef()
	 */
	@Test
	public void testingDuplicateTranactionRef_positve() {
		aReportModel.setReference("111121");
		assertFalse(aApplicationCommonUtility.isDuplicateTranactionRef(aReportModel));
	}
	
	/**
	 * Testing negative case for ApplicationCommonUtility.isDuplicateTranactionRef()
	 */
	@Test
	public void testingDuplicateTranactionRef_negative() {
		aBusinessProcessor.transactionReferenceNumber.add("111111");
		aReportModel.setReference("111111");
		assertTrue(aApplicationCommonUtility.isDuplicateTranactionRef(aReportModel));
	}
	
	/**
	 * Testing positive case for ApplicationCommonUtility.isEndBalanceCorrect()
	 */
	@Test
	public void testingEndBalanceCorrect_positive() {
		aReportModel.setStartBalance("100");
		aReportModel.setMutation("+50.0");
		aReportModel.setEndBalance("150.0");
		
		assertTrue(aApplicationCommonUtility.isEndBalanceCorrect(aReportModel));
	}
	
	/**
	 * Testing negative case for ApplicationCommonUtility.isEndBalanceCorrect()
	 */
	@Test
	public void testingEndBalanceCorrect_negative1() {
		aReportModel.setStartBalance("100");
		aReportModel.setMutation("-50.0");
		aReportModel.setEndBalance("150.0");
		
		assertFalse(aApplicationCommonUtility.isEndBalanceCorrect(aReportModel));
	}
	
	/**
	 * Testing negative case for ApplicationCommonUtility.isEndBalanceCorrect()
	 */
	@Test
	public void testingEndBalanceCorrect_negative2() {
		aReportModel.setStartBalance("100");
		aReportModel.setMutation("+50.0");
		aReportModel.setEndBalance("10.0");
		
		assertFalse(aApplicationCommonUtility.isEndBalanceCorrect(aReportModel));
	}
	
	/**
	 * Testing positive case for BusinessProcessor.process()
	 */
	@Test
	public void testingBusinessProcessor() throws Exception{
		aReportModel.setStartBalance("100");
		aReportModel.setMutation("+50.0");
		aReportModel.setEndBalance("150.0");
		aReportModel.setReference("123456");
		
		assertNull(aBusinessProcessor.process(aReportModel));
		
	}
	
	/**
	 * Testing negative case for BusinessProcessor.process()
	 */
	@Test
	public void testingBusinessProcessor_negative1() throws Exception{
		aReportModel.setStartBalance("100");
		aReportModel.setMutation("-50.0");
		aReportModel.setEndBalance("150.0");
		aReportModel.setReference("123456");
		
		assertNotNull(aBusinessProcessor.process(aReportModel));
		
	}
	
	/**
	 * Testing negative case for BusinessProcessor.process()
	 */
	@Test
	public void testingBusinessProcessor_negative2() throws Exception{
		aReportModel.setStartBalance("100");
		aReportModel.setMutation("-50.0");
		aReportModel.setEndBalance("150.0");
		aReportModel.setReference("123456");
		
		aBusinessProcessor.transactionReferenceNumber.add("123456");
		
		assertNotNull(aBusinessProcessor.process(aReportModel));
		
	}
	
}
