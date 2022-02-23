package com.terrapay.hm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.terrapay.hm.entity.PatientData;
import com.terrapay.hm.entity.TreatmentData;

@Repository
public interface TreatmentDataRepository extends JpaRepository<TreatmentData, Long> {

	TreatmentData findByPatientData(PatientData patientdata);

	Optional<TreatmentData> findByPatientDataId(long id);

}
