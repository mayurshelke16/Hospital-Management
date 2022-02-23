package com.terrapay.hm.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terrapay.hm.entity.PatientData;
import com.terrapay.hm.service.PatientDataService;

@RestController
@RequestMapping("/patient")
public class PatientDataController {

	private static final Logger log = LoggerFactory.getLogger(PatientDataController.class);

	@Autowired
	private PatientDataService patientService;

	@PostMapping("/add")
	public ResponseEntity<PatientData> addPatient(@Valid @RequestBody PatientData patient) {
		log.info("Create new Patient");
		PatientData patient1 = patientService.addPtientDetails(patient);
		return new ResponseEntity<PatientData>(patient1, HttpStatus.CREATED);

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<PatientData>> getAllPatientData() {
		log.info("Get  all Ptient list");
		List<PatientData> patientList = patientService.gatPatientData();
		return new ResponseEntity<List<PatientData>>(patientList, HttpStatus.FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PatientData> getPatientById(@PathVariable Long id) {
		log.info("Get Patient deatil  by id:"+id);
		PatientData patient = patientService.getPtientId(id);
		return new ResponseEntity<PatientData>(patient, HttpStatus.FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<PatientData> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientData patient) {
		log.info("Update Patient deatils by id:"+id);
		PatientData patient2 = patientService.updatePatientDetails(id, patient);
		return new ResponseEntity<PatientData>(patient2, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePatientById(@PathVariable Long id) {
		log.info("Delete patient by id:"+id);
		patientService.deletePatient(id);
		return new ResponseEntity<String>("Patient is delete", HttpStatus.OK);

	}
}
