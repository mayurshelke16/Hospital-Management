package com.terrapay.hm.service;

import com.terrapay.hm.entity.BillData;

public interface BillDataService {

	BillData findBillById(long id);

	BillData updateById(long id);

}
