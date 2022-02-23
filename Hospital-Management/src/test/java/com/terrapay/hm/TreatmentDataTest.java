package com.terrapay.hm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.terrapay.hm.entity.DoctorData;
import com.terrapay.hm.entity.PatientData;
import com.terrapay.hm.entity.TreatmentData;
import com.terrapay.hm.repository.DoctorDataRepository;
import com.terrapay.hm.repository.PatientDataRepository;
import com.terrapay.hm.repository.TreatmentDataRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class TreatmentDataTest {

	@Autowired
	private PatientDataRepository patientDataRepository;
	@Autowired
	private TreatmentDataRepository treatmentDataRepository;

	@Autowired
	private DoctorDataRepository doctorDataRepository;

	@Test
	@Order(1)
	public void testAssingPatient() {

		PatientData patientData = patientDataRepository.findById(1L).get();
		assertThat(patientData.getId()).isNotNull();

		DoctorData doctorData = doctorDataRepository.findById(1L).get();

		assertThat(doctorData.getId()).isNotNull();
		TreatmentData treatmentData = new TreatmentData();
		treatmentData.setId(1L);
		treatmentData.setPer_Day_Charge(1500);
		treatmentData.setPatientData(patientData);
		treatmentData.setDoctorData(doctorData);
		treatmentDataRepository.save(treatmentData);
		assertThat(treatmentDataRepository.getById(1L)).isNotNull();
	}

	@Test
	@Order(2)
	public void checkIn() {
		TreatmentData treatmentData = treatmentDataRepository.findById(1L).get();
		treatmentData.setCheckIn(new Date());
		treatmentData.setUpdatedOn(new Date());
		treatmentDataRepository.save(treatmentData);
		assertNotEquals(new Date(), treatmentDataRepository.findById(1L).get().getCheckIn());

	}

	@Test
	@Order(3)
	public void checkOut() {
		TreatmentData treatmentData = treatmentDataRepository.findById(1L).get();
		treatmentData.setCheckOut(new Date());
		treatmentData.setUpdatedOn(new Date());
		treatmentDataRepository.save(treatmentData);
		assertNotEquals(new Date(), treatmentDataRepository.findById(1L).get().getCheckIn());

	}

	@Test
	@Order(4)
	public void testTreatmentById() {
		TreatmentData treatmentData = treatmentDataRepository.findById(1L).get();
		assertEquals("Suraj Patil", treatmentData.getPatientData().getName());
	}

}
