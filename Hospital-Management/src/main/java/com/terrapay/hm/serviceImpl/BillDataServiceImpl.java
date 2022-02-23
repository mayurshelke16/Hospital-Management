package com.terrapay.hm.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrapay.hm.entity.BillData;
import com.terrapay.hm.exception.ResourceNotFoundException;
import com.terrapay.hm.repository.BillDataRepository;
import com.terrapay.hm.service.BillDataService;
import com.terrapay.hm.utils.Constant;

@Service
public class BillDataServiceImpl implements BillDataService {

	@Autowired
	private BillDataRepository billRepository;

	@Override
	public BillData findBillById(long id) {

		BillData billData = billRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("treatment id not found"));

		return billData;
	}

	@Override
	public BillData updateById(long id) {

		BillData billData = billRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bill id not found"));

		billData.setStatus(Constant.BILL_PAID);
		billData.setUpdatedOn(new Date());
		return billRepository.save(billData);
	}

	
	
}
