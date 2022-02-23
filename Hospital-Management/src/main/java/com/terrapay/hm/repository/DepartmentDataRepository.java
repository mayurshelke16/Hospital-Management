package com.terrapay.hm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.terrapay.hm.entity.DepartmentData;
@Repository
public interface DepartmentDataRepository extends JpaRepository<DepartmentData, Long> {
	
	
	Optional<DepartmentData> findByName(String name);
	
	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query(value = "update department set is_deleted=true where id=?",nativeQuery
	 * = true) int deleteDeptById(Integer deptId);
	 */

	Optional<DepartmentData> findByIdAndIsDeleted(Long id, boolean isDeletedFalse);

	
}
