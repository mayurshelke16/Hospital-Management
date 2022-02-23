package com.terrapay.hm.controller;

import java.util.List;

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

import com.terrapay.hm.entity.DoctorData;
import com.terrapay.hm.service.DoctorDataService;

@RestController
@RequestMapping("/doctor")
public class DoctorDataController {

	private static final Logger log = LoggerFactory.getLogger(DoctorDataController.class);

	@Autowired
	private DoctorDataService service;

	@PostMapping("/add")
	public ResponseEntity<?> addDoctoreDetails(@RequestBody DoctorData doctor) {
		log.info("Create Doctor details api");
		DoctorData doctor2 = service.saveData(doctor);
		return new ResponseEntity<DoctorData>(doctor2, HttpStatus.CREATED);

	}

	@PutMapping("/Notworking/{id}")
	public ResponseEntity<DoctorData> workingStatusDisplay(@PathVariable long id) {

		DoctorData data = service.changeStatus(id);
		return new ResponseEntity<DoctorData>(data, HttpStatus.CREATED);
	}

	@PutMapping("/working/{id}")
	public ResponseEntity<DoctorData> changeWorkingStatus(@PathVariable long id) {

		DoctorData data = service.changeStatusWork(id);
		return new ResponseEntity<DoctorData>(data, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<DoctorData>> showData() {
		log.info("Get list of doctor");
		List<DoctorData> doctorList = service.getDetail();
		return new ResponseEntity<List<DoctorData>>(doctorList, HttpStatus.FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<DoctorData> updateData(@PathVariable Long id, @RequestBody DoctorData doctor) {
		log.info("Updated doctor details:" + id);
		DoctorData doctorData = service.update(id, doctor);
		return new ResponseEntity<DoctorData>(doctorData, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<DoctorData> getDoctorId(@PathVariable Long id) {
		log.info("Get doctor by id:" + id);
		DoctorData doctor = service.getByDoctorId(id);
		return new ResponseEntity<DoctorData>(doctor, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteData(@PathVariable Long id) {
		log.info("Delete doctor by id:" + id);
		service.deleteDetails(id);
		return new ResponseEntity<String>("Doctor is delete", HttpStatus.OK);

	}

	@GetMapping("/getWorking")
	public ResponseEntity<List<DoctorData>> getAllWorking() {
		log.info("Show working doctor");
		List<DoctorData> doctorList = service.getWorkingDoctor();
		return new ResponseEntity<List<DoctorData>>(doctorList, HttpStatus.FOUND);

	}
	
	@GetMapping("/getNotWorking")
	public ResponseEntity<List<DoctorData>> getAllNotWorking() {
		log.info("Show working doctor");
		List<DoctorData> doctorList = service.getNotWorkingDoctor();
		return new ResponseEntity<List<DoctorData>>(doctorList, HttpStatus.FOUND);

	}
}
