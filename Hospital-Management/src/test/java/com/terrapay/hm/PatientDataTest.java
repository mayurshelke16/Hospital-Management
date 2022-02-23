package com.terrapay.hm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.terrapay.hm.entity.PatientData;
import com.terrapay.hm.repository.PatientDataRepository;
import com.terrapay.hm.utils.Constant;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PatientDataTest {

	@Autowired
	private PatientDataRepository patientDataRepository;

	@Test
	@Order(1)
	public void testCreate() {
		PatientData p = new PatientData();
		p.setId(1L);
		p.setName("Suraj Patil");
		p.setEmail("suraj12@gmail.com");
		p.setAilments("Back pain");
		p.setAddress("pune");
		p.setAllergies("no");
		patientDataRepository.save(p);
		assertNotNull(patientDataRepository.findById(1L).get());
		assertThat(p.getName()).isNotEqualTo("Suraj Pat");
	}

	@Test
	@Order(2)
	public void testGetAll() {
		List<PatientData> list = patientDataRepository.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testGetById() {

		PatientData patientData = patientDataRepository.findById(1L).get();
		assertEquals("Suraj Patil", patientData.getName());
		assertNotEquals("Suraj", patientData.getName());
	}


	@Test
	@Order(4)
	public void testUpdate()
	{
		PatientData patientData=patientDataRepository.findById(1L).get();
		patientData.setName("Suraj Patil");
		patientData.setEmail("suraj123@gmail.com");
		patientData.setAddress("pune");
		patientData.setAilments("Back pain");
		patientData.setAllergies("no");
		patientData.setUpdate_on(new Date());
		patientDataRepository.save(patientData);
		assertNotEquals("Akurdi", patientDataRepository.findById(1L).get().getAddress());
	}
	
	@Test
	@Order(5)
	public void testUpdateById()
	{
		PatientData patientData=patientDataRepository.findById(1L).get();
		patientData.setName("Suraj Patil");
		patientData.setEmail("suraj123@gmail.com");
		patientData.setAddress("pune");
		patientData.setAilments("Back pain");
		patientData.setAllergies("no");
		patientData.setUpdate_on(new Date());
		patientDataRepository.save(patientData);
		assertEquals("pune", patientDataRepository.findById(1L).get().getAddress());
	}
	

	@Test
	@Order(6)
	public void testDelete()
	{
		PatientData patientData=patientDataRepository.findById(1L).get();
		patientData.setDeleted(Constant.IS_DELETED_TRUE);
        patientDataRepository.save(patientData);
		assertThat(patientDataRepository.existsById(1L)).isTrue();
	}
	
}
