package com.terrapay.hm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.terrapay.hm.entity.BillData;
@Repository
public interface BillDataRepository extends JpaRepository<BillData, Long>{

}
