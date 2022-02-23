package com.terrapay.hm.service;

import java.util.List;

import com.terrapay.hm.entity.PatientData;

public interface PatientDataService {

	PatientData addPtientDetails(PatientData patient);

	List<PatientData> gatPatientData();

	PatientData getPtientId(long id);

	String deletePatient(long id);

	PatientData updatePatientDetails(long id, PatientData patient);

}
