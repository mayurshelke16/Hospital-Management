package com.terrapay.hm.service;

import com.terrapay.hm.entity.TreatmentData;

public interface TreatmentDataService {

	TreatmentData addTreatmentDetails(TreatmentData treatmentData);

	TreatmentData updateCheckin(Long id,TreatmentData treatmentData);

	TreatmentData updateCheckout(Long id);

	TreatmentData getTreatmentById(long id);

	TreatmentData getSummaryDetails(long id);

}
