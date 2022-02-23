package com.terrapay.hm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.terrapay.hm.entity.PatientData;
@Repository
public interface PatientDataRepository extends JpaRepository<PatientData, Long> {


	List<PatientData> findByIsDeleted(boolean isDeletedFalse);

	Optional<PatientData> findByIdAndIsDeleted(long id, boolean isDeletedFalse);

	Optional<PatientData> findByEmail(String email);
}
