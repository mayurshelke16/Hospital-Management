package com.terrapay.hm.serviceImpl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrapay.hm.entity.DepartmentData;
import com.terrapay.hm.entity.DoctorData;
import com.terrapay.hm.exception.ResourceNotFoundException;
import com.terrapay.hm.repository.DepartmentDataRepository;
import com.terrapay.hm.repository.DoctorDataRepository;
import com.terrapay.hm.service.DoctorDataService;
import com.terrapay.hm.utils.Constant;

@Service
public class DoctorDataServiceImpl implements DoctorDataService {



	@Autowired
	private DoctorDataRepository repository;

	@Autowired
	private DepartmentDataRepository departmentRepository;

	@Override
	public DoctorData saveData(DoctorData doctor) {
		DepartmentData department = departmentRepository.findByIdAndIsDeleted(doctor.getDepartment().getId(),Constant.IS_DELETED_FALSE)
				.orElseThrow(() -> new ResourceNotFoundException("invalid department id"));
				doctor.setDepartment(department);
				doctor.setIsWorking(Constant.status_Working);
			doctor.setDeleted(Constant.IS_DELETED_FALSE);
	 DoctorData data= repository.save(doctor);
		return data;
		
	}

	
	@Override
	public List<DoctorData> getDetail() {
		List<DoctorData> listDoctor = repository.findByIsDeleted(Constant.IS_DELETED_FALSE);
		return listDoctor;
	}

	

	@Override
	public DoctorData getByDoctorId(Long id) {
		return repository.findByIdAndIsDeleted(id,Constant.IS_DELETED_FALSE)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id :" + id));
	}

	@Override
	public String deleteDetails(Long id) {
		DoctorData doctorData = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id :" + id));
			doctorData.setDeleted(Constant.IS_DELETED_TRUE);
			repository.save(doctorData);
	return "data is delete";
	}

	@Override
	public DoctorData update(Long id, DoctorData doctord) {
		DoctorData doctor = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id :" + id));

		doctor.setName(doctord.getName());
		doctor.setSpecialization(doctord.getSpecialization());
		//doctor.setWorking(doctord.isWorking());
		doctor.setWorkingHours(doctord.getWorkingHours());
		doctor.setFee(doctord.getFee());

		doctor.setUpdated_on(Timestamp.from(Instant.now()));
		doctor.setDepartment(doctord.getDepartment());
        
		return repository.save(doctor);
	}


	@Override
	public DoctorData changeStatus(long id) {
		 DoctorData data=repository.findByIdAndIsDeleted(id,Constant.IS_DELETED_FALSE)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id :" + id));		
		
		 data.setIsWorking(Constant.status_Not_working);
		 
		return repository.save(data);
	}


	@Override
	public DoctorData changeStatusWork(long id) {
		 DoctorData data=repository.findByIdAndIsDeleted(id,Constant.IS_DELETED_FALSE)
					.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id :" + id));		
			
			 data.setIsWorking(Constant.status_Working);
			 
			return repository.save(data);
	}


	@Override
	public List<DoctorData> getWorkingDoctor() {
		List<DoctorData> doctorData= repository.findByIsDeletedAndIsWorking(Constant.IS_DELETED_FALSE,Constant.status_Working);

		return doctorData;
	}


	@Override
	public List<DoctorData> getNotWorkingDoctor() {
		List<DoctorData> doctorData= repository.findByIsDeletedAndIsWorking(Constant.IS_DELETED_FALSE,Constant.status_Not_working);

		return doctorData;
	}

}
