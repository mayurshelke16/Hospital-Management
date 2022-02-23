package com.terrapay.hm.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrapay.hm.entity.DepartmentData;
import com.terrapay.hm.exception.DataAlreadyExistException;
import com.terrapay.hm.exception.ResourceNotFoundException;
import com.terrapay.hm.repository.DepartmentDataRepository;
import com.terrapay.hm.service.DepartmentDataService;
import com.terrapay.hm.utils.Constant;

@Service
public class DepartmentDataServiceImpl implements DepartmentDataService {

	@Autowired
	private DepartmentDataRepository repository;

	@Override
	public DepartmentData addDepartmentDetails(DepartmentData department) {

		Optional<DepartmentData> existData = repository.findByName(department.getName());
		if (existData.isPresent()) {
			throw new DataAlreadyExistException("department id  already exist");
		}
		department.setDeleted(Constant.IS_DELETED_FALSE);
		DepartmentData departmentData = repository.save(department);
		return departmentData;

	}

	@Override
	public DepartmentData getDepartment(Long id) {
		DepartmentData data = repository.findByIdAndIsDeleted(id, Constant.IS_DELETED_FALSE)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id :" + id));
		return data;

	}

	@Override
	public List<DepartmentData> getAllDepartments() {
		List<DepartmentData> findAll = repository.findAll().stream().filter(d -> !d.isDeleted())
				.collect(Collectors.toList());

		return findAll;

	}

	@Override
	public String deleteDepartment(Long id) {
		DepartmentData data = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id :" + id));
		data.setDeleted(Constant.IS_DELETED_TRUE);
		repository.save(data);
		return "data is delete";

	}

	@Override
	public DepartmentData updateDepartment(Long id, DepartmentData departmentdata) {

		DepartmentData data = repository.findByIdAndIsDeleted(id, Constant.IS_DELETED_FALSE)
				.orElseThrow(() -> new ResourceNotFoundException("department not found with id :" + id));

		if (!data.getName().equalsIgnoreCase(departmentdata.getName())) {
			Optional<DepartmentData> existData = repository.findByName(departmentdata.getName());

			if (existData.isPresent())
				throw new DataAlreadyExistException("data already exist");
		}

		data.setName(departmentdata.getName());

		data.setUpdatedOn(new Date());
		return repository.save(data);

	}

}
