package com.terrapay.hm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terrapay.hm.entity.TreatmentData;
import com.terrapay.hm.service.TreatmentDataService;

@RestController
@RequestMapping("/treatment")
public class TreatmentDataController {

	
	private static final Logger log = LoggerFactory.getLogger(TreatmentDataController.class);

	
	@Autowired
	private TreatmentDataService service;

	@PostMapping("/assign")
	public ResponseEntity<TreatmentData> addTreatment(@RequestBody TreatmentData treatmentData) {
log.info("Assign ptient to the doctor ");
		TreatmentData data = service.addTreatmentDetails(treatmentData);
		return new ResponseEntity<>(data, HttpStatus.CREATED);
	}

	@PutMapping("/checkin/{id}")
	public ResponseEntity<TreatmentData> checkInPatient(@PathVariable Long id,@RequestBody TreatmentData treatmentData) {

		log.info("Checkin api");
		TreatmentData data = service.updateCheckin(id,treatmentData);
		return new ResponseEntity<TreatmentData>(data, HttpStatus.OK);

	}
	
	@PutMapping("/checkout/{id}")
	public ResponseEntity<TreatmentData> checkOutPatient(@PathVariable Long id){
log.info("Checkout patient  and generate bill:"+id);		
		return new ResponseEntity<TreatmentData>(service.updateCheckout(id), HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TreatmentData> getTreatmentDetails(@PathVariable long id)
	{
		log.info("Get treatment details by id:"+id);
		return new ResponseEntity<TreatmentData>(service.getTreatmentById(id),HttpStatus.OK);
		
	}
	
	
	
	
}
