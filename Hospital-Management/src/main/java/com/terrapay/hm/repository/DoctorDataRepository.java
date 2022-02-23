package com.terrapay.hm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.terrapay.hm.entity.DoctorData;

@Repository
public interface DoctorDataRepository extends JpaRepository<DoctorData, Long>{

	List<DoctorData> findByIsDeleted(boolean isDeletedFalse);

	Optional<DoctorData> findByIdAndIsDeleted(Long id, boolean isDeletedFalse);

	Optional<DoctorData> findByIdAndIsDeletedAndIsWorking(long id, boolean isDeletedFalse, String statusWorking);

	List<DoctorData> findByIsDeletedAndIsWorking(boolean isDeletedFalse, String statusWorking);

//	Optional<DoctorData> findByIdAndIsWorking(long id, boolean statusWorking);

}
