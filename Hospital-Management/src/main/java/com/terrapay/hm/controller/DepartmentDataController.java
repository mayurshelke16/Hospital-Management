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

import com.terrapay.hm.entity.DepartmentData;
import com.terrapay.hm.service.DepartmentDataService;

@RestController
@RequestMapping("/department")
public class DepartmentDataController {

	private static final Logger log = LoggerFactory.getLogger(DepartmentDataController.class);

	@Autowired
	private DepartmentDataService service;

	@PostMapping("/add")
	public ResponseEntity<DepartmentData> addDepartment(@Valid @RequestBody DepartmentData department) {
		log.info("Create new Department");
		DepartmentData data = service.addDepartmentDetails(department);
		return new ResponseEntity<DepartmentData>(data, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentData> getDepartment(@PathVariable Long id) {
		log.info("Get department by id:"+id);
		return new ResponseEntity<DepartmentData>(service.getDepartment(id), HttpStatus.OK);

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<DepartmentData>> getAllDepartments() {
		log.info("Get all department list ");
		List<DepartmentData> listData = service.getAllDepartments();
		return new ResponseEntity<List<DepartmentData>>(listData, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
		log.info("Delete department by id:"+id);
		return new ResponseEntity<String>(service.deleteDepartment(id), HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<DepartmentData> updateDepartment(@PathVariable Long id,
			@Valid @RequestBody DepartmentData departmentData) {
		log.info("Update department details by id:"+id);
		DepartmentData data = service.updateDepartment(id, departmentData);
		return new ResponseEntity<DepartmentData>(data, HttpStatus.OK);

	}
}
