package com.bank.customer.statement.processor.CustomerStatementProcessor.processor;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.batch.item.ItemProcessor;

import com.bank.customer.statement.processor.CustomerStatementProcessor.model.ReportModel;
import com.bank.customer.statement.processor.CustomerStatementProcessor.utils.ApplicationCommonUtility;

/**
 * 
 * @author Tanmay Kumar Patra
 *	This class is the business processor class which would contain all business related validation
 *	resulting in filtration of transaction
 */

public class BusinessProcessor implements ItemProcessor<ReportModel, ReportModel> {
	
	private static final Logger LOGGER = Logger.getLogger(BusinessProcessor.class.getName());
	
	public static ArrayList<String> transactionReferenceNumber = new ArrayList<String>();

	@Override
	public ReportModel process(ReportModel aXMLReportModel) throws Exception {
		
		//Checking for duplicate Transaction reference number
		if(ApplicationCommonUtility.isDuplicateTranactionRef(aXMLReportModel)) {
			//forwarding the transaction record to the writer section for writing to error record file
			return aXMLReportModel;
		}
		
		if(! ApplicationCommonUtility.isEndBalanceCorrect(aXMLReportModel)) {
			//forwarding the transaction record to the writer section for writing to error record file
			return aXMLReportModel;
		}
		//Adding the reference number to the processed list
		transactionReferenceNumber.add(aXMLReportModel.getReference());
		LOGGER.info("TRANSACTION WITH REFERENCE NUMBER " + aXMLReportModel.getReference() + " IS AS EXPECTED");
		//If the transaction is as expected no action is required and hence returning null which would return control to the reader
		return null;
	}

}
