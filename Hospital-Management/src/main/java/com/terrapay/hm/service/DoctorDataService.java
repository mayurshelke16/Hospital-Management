package com.terrapay.hm.service;

import java.util.List;

import com.terrapay.hm.entity.DoctorData;

public interface DoctorDataService {

	
	DoctorData saveData(DoctorData doctor);

	List<DoctorData> getDetail();

	
	DoctorData getByDoctorId(Long id);

	String deleteDetails(Long id);

	DoctorData update(Long id, DoctorData doctor);

	DoctorData changeStatus(long id);

	DoctorData changeStatusWork(long id);

	List<DoctorData> getWorkingDoctor();

	List<DoctorData> getNotWorkingDoctor();
}
