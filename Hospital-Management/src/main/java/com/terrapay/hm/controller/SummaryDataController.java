package com.terrapay.hm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terrapay.hm.entity.TreatmentData;
import com.terrapay.hm.service.TreatmentDataService;

@RestController
@RequestMapping("/summary")
public class SummaryDataController {

	private static final Logger log = LoggerFactory.getLogger(SummaryDataController.class);

	@Autowired
	private TreatmentDataService service;

	@GetMapping("/{id}")
	public ResponseEntity<TreatmentData> getSummaryDetails(@PathVariable long id) {
		log.info("Get details patient by id ,whoose treate and bill information:"+id);
		return new ResponseEntity<TreatmentData>(service.getSummaryDetails(id), HttpStatus.OK);

	}

}
