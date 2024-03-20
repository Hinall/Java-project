package com.digitalgis.service;

import org.springframework.stereotype.Service;

@Service
public interface ConfigurationTaxRateService {

	String getTaxRateMasterData();

	String getMiscTaxRateMasterData();

	String getAnnualTaxRateMasterData();

	String getTaxRebateMatrixMasterData();

	String getTaxZoneMasterData();
	
}
