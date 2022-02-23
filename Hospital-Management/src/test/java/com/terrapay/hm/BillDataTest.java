package com.terrapay.hm;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.terrapay.hm.entity.BillData;
import com.terrapay.hm.repository.BillDataRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BillDataTest {

	@Autowired
	private BillDataRepository repository;

	@Test
	@Order(1)
	public void testGetById() {
		BillData billData = repository.findById(1L).get();
		assertEquals("UNPAID", billData.getStatus());
	}

	@Test
	@Order(2)
	public void testUpdateById() {
		BillData billData = repository.findById(2L).get();
		billData.setStatus("PAID");
		billData.setUpdatedOn(new Date());
		assertEquals("PAID", billData.getStatus());
	}

}
