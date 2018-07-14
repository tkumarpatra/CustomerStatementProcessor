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
	 * @param aReportModel
	 * @return 	<b>true</b> if the transaction is duplicate<br/>
	 * 			<b>false</b> if the transaction is unique
	 */
	public static boolean isDuplicateTranactionRef(ReportModel aReportModel) {
		if (BusinessProcessor.transactionReferenceNumber.contains(aReportModel.getReference())) {
			// Log the duplicate transaction
			LOGGER.warning("DUPLICATE TRANSACTION NUMBER " + aReportModel.getReference() + " ENCOUNTERED");
			return true;
		}
		return false;
	}
	
	/**
	 * This method calculates the ending amount by mutation of starting amount and the mutation amount
	 * @param aReportModel
	 * @return  <b>true</b> if the end amount matches the calculated end amount<br/>
	 * 			<b>false</b> if the end amount does not match the calculated end amount
	 */
	public static boolean isEndBalanceCorrect(ReportModel aReportModel) {

		// Check addition or deduction
		//Apply math based on + or - and compare with end balance attribute
		if (aReportModel.getMutation().contains("+")) {
			BigDecimal calculatedEndBalance = new BigDecimal(aReportModel.getStartBalance())
					.add(new BigDecimal(aReportModel.getMutation().substring(1)));
			if (calculatedEndBalance.compareTo(new BigDecimal(aReportModel.getEndBalance())) == 0) {
				return true;
			}
		} else if (aReportModel.getMutation().contains("-")) {
			BigDecimal calculatedEndBalance = new BigDecimal(aReportModel.getStartBalance())
					.subtract(new BigDecimal(aReportModel.getMutation().substring(1)));
			if (calculatedEndBalance.compareTo(new BigDecimal(aReportModel.getEndBalance())) == 0) {
				return true;
			}
		}
		//Log the error reference number
		LOGGER.warning("END BALANCE FOR TRANSACTION REFERENCE NUMBER " + aReportModel.getReference() + "IS NOT MATCHING WITH APPLIED MUTATION");
		return false;
	}

}
