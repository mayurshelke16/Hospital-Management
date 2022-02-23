package com.terrapay.hm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.terrapay.hm.entity.DepartmentData;
import com.terrapay.hm.repository.DepartmentDataRepository;
import com.terrapay.hm.utils.Constant;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DepartmentDataTest {

	@Autowired
	private DepartmentDataRepository repository;

	@Test
	@Order(1)
	public void testCreate() {

		DepartmentData departmentData = new DepartmentData();
		departmentData.setId(1L);
		departmentData.setName("spine surgeon");
		departmentData.setFee(800);
		repository.save(departmentData);
		assertNotNull(repository.findById(1L).get());

	}

	@Test
	@Order(2)
	public void testGetAll() {
		List<DepartmentData> list = repository.findAll();
		assertThat(list).size().isGreaterThan(1);

	}

	@Test
	@Order(3)
	public void testGetById() {
		DepartmentData departmentData = repository.findById(1L).get();
		assertNotEquals("General surgeon", departmentData.getName());
	}

	@Test
	@Order(4)
	public void testUpdate() {
		DepartmentData departmentData = repository.findById(1L).get();
		departmentData.setFee(800);
		repository.save(departmentData);
		assertNotEquals(750, repository.findById(1L).get().getFee());
	}

	@Test
	@Order(5)
	public void testDelete() {
		DepartmentData departmentData = repository.findById(1L).get();
		departmentData.setDeleted(Constant.IS_DELETED_TRUE);
		repository.save(departmentData);
		assertThat(repository.existsById(1L)).isTrue();

	}

}
