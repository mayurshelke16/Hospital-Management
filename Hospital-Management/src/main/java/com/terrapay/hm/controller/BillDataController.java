package com.terrapay.hm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terrapay.hm.entity.BillData;
import com.terrapay.hm.service.BillDataService;

@RestController
@RequestMapping("/bill")
public class BillDataController {

	private static final Logger log = LoggerFactory.getLogger(BillDataController.class);

	@Autowired
	private BillDataService billService;

	@GetMapping("/{id}")
	public ResponseEntity<BillData> getBillById(@PathVariable long id) {
		log.info("get bill details by id:"+id);
		BillData billData = billService.findBillById(id);
		return new ResponseEntity<BillData>(billData, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<BillData> updateBill(@PathVariable long id) {
		log.info("Update bill by id:"+id);
		return new ResponseEntity<BillData>(billService.updateById(id), HttpStatus.OK);

	}
}
