package com.terrapay.hm.controller;

import java.util.List;

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

	@Autowired
	private DoctorDataService service;

	@PostMapping("/add")
	public ResponseEntity<?> addDoctoreDetails(@RequestBody DoctorData doctor) {
		DoctorData doctor2 = service.saveData(doctor);
		return new ResponseEntity<DoctorData>(doctor2, HttpStatus.CREATED);

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<DoctorData>> showData() {
		List<DoctorData> doctorList = service.getDetail();
		return new ResponseEntity<List<DoctorData>>(doctorList, HttpStatus.FOUND);

	}

	@PutMapping("/{id}")
	public ResponseEntity<DoctorData> updateData(@PathVariable Long id, @RequestBody DoctorData doctor) {
		DoctorData doctorData = service.update(id, doctor);
		return new ResponseEntity<DoctorData>(doctorData, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<DoctorData> getDoctorId(@PathVariable Long id) {
		DoctorData doctor = service.getByDoctorId(id);
		return new ResponseEntity<DoctorData>(doctor, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteData(@PathVariable Long id) {

		service.deleteDetails(id);
		return new ResponseEntity<String>("Doctor is delete",HttpStatus.OK);

	}

}
