package com.terrapay.hm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.terrapay.hm.entity.DepartmentData;
import com.terrapay.hm.entity.DoctorData;
import com.terrapay.hm.repository.DepartmentDataRepository;
import com.terrapay.hm.repository.DoctorDataRepository;
import com.terrapay.hm.utils.Constant;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DoctorDataTest {

	@Autowired
	private DoctorDataRepository doctorDataRepository;

	@Autowired
	private DepartmentDataRepository departmentDataRepository;

	@Test
	@Order(1)
	public void testCreate() {
		DepartmentData departmentData = departmentDataRepository.findById(1L).get();
		assertThat(departmentData.getId()).isNotNull();
		DoctorData doctorData = new DoctorData();
		doctorData.setId(1L);
		doctorData.setName("Dr.Rajesh Patil");
		doctorData.setFee(750);
		doctorData.setSpecialization("MBBS");
		doctorData.setDepartment(departmentData);
		doctorDataRepository.save(doctorData);
		assertThat(doctorDataRepository.getById(1L)).isNotNull();

	}

	@Test
	@Order(2)
	public void testGetAll() {
		List<DoctorData> list = doctorDataRepository.findAll();
		assertThat(list).size().isGreaterThan(1);
	}

	@Test
	@Order(3)
	public void testGetById() {
		DoctorData doctorData = doctorDataRepository.findById(1L).get();
		assertNotEquals("Dr.Rajesh", doctorData.getName());

	}

	@Test
	@Order(4)
	public void testUpdate() {
		DoctorData doctorData = doctorDataRepository.findById(1L).get();
		doctorData.setName("Dr.Rajesh Patil");
		doctorData.setFee(750);
		doctorData.setSpecialization("MBBS");
		
		doctorData.setUpdated_on(new Date());
		doctorDataRepository.save(doctorData);
		assertEquals(750, doctorDataRepository.findById(1L).get().getFee());
	}

	@Test
	@Order(5)
	public void testDelete() {
		DoctorData doctorData = doctorDataRepository.findById(1L).get();
		doctorData.setDeleted(Constant.IS_DELETED_TRUE);
		doctorDataRepository.save(doctorData);
		assertThat(doctorDataRepository.existsById(1L)).isTrue();
	}
}
