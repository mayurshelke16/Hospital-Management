package com.terrapay.hm.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrapay.hm.entity.PatientData;
import com.terrapay.hm.exception.DataAlreadyExistException;
import com.terrapay.hm.exception.ResourceNotFoundException;
import com.terrapay.hm.repository.PatientDataRepository;
import com.terrapay.hm.service.PatientDataService;
import com.terrapay.hm.utils.Constant;

@Service
public class PatientDataServiceImpl implements PatientDataService {

	@Autowired
	private PatientDataRepository patientRepository;

	@Override
	public PatientData addPtientDetails(PatientData patient) {
		Optional<PatientData> existData = patientRepository.findByEmail(patient.getEmail());
		if (existData.isPresent()) {
			throw new DataAlreadyExistException("Email id  already exist");
		}
		patient.setDeleted(Constant.IS_DELETED_FALSE);
		PatientData patientAdd = patientRepository.save(patient);
		return patientAdd;

	}

	@Override
	public List<PatientData> gatPatientData() {
		List<PatientData> patientlist = patientRepository.findByIsDeleted(Constant.IS_DELETED_FALSE);
		return patientlist;
	}

	@Override
	public PatientData getPtientId(long id) {
		return patientRepository.findByIdAndIsDeleted(id, Constant.IS_DELETED_FALSE)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id :" + id));

	}

	@Override
	public String deletePatient(long id) {
		PatientData patientData = patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id :" + id));
		patientData.setDeleted(Constant.IS_DELETED_TRUE);
		patientRepository.save(patientData);
		return "data is delete";
	}

	@Override
	public PatientData updatePatientDetails(long id, PatientData patient) {
		Optional<PatientData> existPatient = patientRepository.findByIdAndIsDeleted(id, Constant.IS_DELETED_FALSE);
		if (!existPatient.isPresent()) {
			throw new ResourceNotFoundException("Patient not found with id :" + id);
		}
		if (!existPatient.get().getEmail().equalsIgnoreCase(patient.getEmail())) {
			Optional<PatientData> existData = patientRepository.findByEmail(patient.getEmail());
			if (existData.isPresent())
				throw new DataAlreadyExistException("data already exist");
		}
		PatientData patientData = existPatient.get();
		patientData.setName(patient.getName());
		patientData.setEmail(patient.getEmail().toLowerCase());
		patientData.setAddress(patient.getAddress());
		patientData.setAilments(patient.getAilments());
		patientData.setAllergies(patient.getAllergies());
		patientData.setUpdate_on(new Date());
		return patientRepository.save(patientData);
	}

}
