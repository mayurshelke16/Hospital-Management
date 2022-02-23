package com.terrapay.hm.service;


import java.util.List;

import com.terrapay.hm.entity.DepartmentData;

public interface DepartmentDataService {

	DepartmentData addDepartmentDetails(DepartmentData department);

	DepartmentData getDepartment(Long id);

	List<DepartmentData> getAllDepartments();

	String deleteDepartment(Long id);

	DepartmentData updateDepartment(Long id, DepartmentData departmentData);

}
