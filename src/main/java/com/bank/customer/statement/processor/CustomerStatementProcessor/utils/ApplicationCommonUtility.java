package com.bank.customer.statement.processor.CustomerStatementProcessor.utils;

import java.math.BigDecimal;
import java.util.logging.Logger;

import com.bank.customer.statement.processor.CustomerStatementProcessor.model.ReportModel;
import com.bank.customer.statement.processor.CustomerStatementProcessor.processor.BusinessProcessor;

/**
 * This class contains the utility methods used in the application
 * @author Tanmay Kumar Patra
 *
 */
public class ApplicationCommonUtility {

	private static final Logger LOGGER = Logger.getLogger(ApplicationCommonUtility.class.getName());
	
	/**
	 * This method checks whether the transaction reference number is duplicate or not
	 * @param aXMLReportModel
	 * @return 	<b>true</b> if the transaction is duplicate<br/>
	 * 			<b>false</b> if the transaction is unique
	 */
	public static boolean isDuplicateTranactionRef(ReportModel aXMLReportModel) {
		if (BusinessProcessor.transactionReferenceNumber.contains(aXMLReportModel.getReference())) {
			// Log the duplicate transaction
			LOGGER.warning("DUPLICATE TRANSACTION NUMBER " + aXMLReportModel.getReference() + " ENCOUNTERED");
			return true;
		}
		return false;
	}
	
	/**
	 * This method calculates the ending amount by mutation of starting amount and the mutation amount
	 * @param aXMLReportModel
	 * @return  <b>true</b> if the end amount matches the calculated end amount<br/>
	 * 			<b>false</b> if the end amount does not match the calculated end amount
	 */
	public static boolean isEndBalanceCorrect(ReportModel aXMLReportModel) {

		// Check addition or deduction
		//Apply math based on + or - and compare with end balance attribute
		if (aXMLReportModel.getMutation().contains("+")) {
			BigDecimal calculatedEndBalance = new BigDecimal(aXMLReportModel.getStartBalance())
					.add(new BigDecimal(aXMLReportModel.getMutation().substring(1)));
			if (calculatedEndBalance.compareTo(new BigDecimal(aXMLReportModel.getEndBalance())) == 0) {
				return true;
			}
		} else if (aXMLReportModel.getMutation().contains("-")) {
			BigDecimal calculatedEndBalance = new BigDecimal(aXMLReportModel.getStartBalance())
					.subtract(new BigDecimal(aXMLReportModel.getMutation().substring(1)));
			if (calculatedEndBalance.compareTo(new BigDecimal(aXMLReportModel.getEndBalance())) == 0) {
				return true;
			}
		}
		//Log the error reference number
		LOGGER.warning("END BALANCE FOR TRANSACTION REFERENCE NUMBER " + aXMLReportModel.getReference() + "IS NOT MATCHING WITH APPLIED MUTATION");
		return false;
	}

}
