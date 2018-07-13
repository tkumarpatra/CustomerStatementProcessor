package com.bank.customer.statement.processor.CustomerStatementProcessor.processor;

import org.springframework.batch.item.ItemProcessor;

import com.bank.customer.statement.processor.CustomerStatementProcessor.model.XMLReportModel;

public class BusinessProcessor implements ItemProcessor<XMLReportModel, XMLReportModel> {

	@Override
	public XMLReportModel process(XMLReportModel aXMLReportModel) throws Exception {
		// TODO Auto-generated method stub
		return aXMLReportModel;
	}

}
