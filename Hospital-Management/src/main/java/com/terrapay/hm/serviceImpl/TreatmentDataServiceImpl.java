package com.terrapay.hm.serviceImpl;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrapay.hm.entity.BillData;
import com.terrapay.hm.entity.DoctorData;
import com.terrapay.hm.entity.PatientData;
import com.terrapay.hm.entity.TreatmentData;
import com.terrapay.hm.exception.AlredyCheckOutException;
import com.terrapay.hm.exception.DataAlreadyExistException;
import com.terrapay.hm.exception.ResourceNotFoundException;
import com.terrapay.hm.repository.BillDataRepository;
import com.terrapay.hm.repository.DoctorDataRepository;
import com.terrapay.hm.repository.PatientDataRepository;
import com.terrapay.hm.repository.TreatmentDataRepository;
import com.terrapay.hm.service.TreatmentDataService;
import com.terrapay.hm.utils.Constant;

@Service
public class TreatmentDataServiceImpl implements TreatmentDataService {

	@Autowired
	private TreatmentDataRepository treatmentRepository;

	@Autowired
	private PatientDataRepository patientRepository;

	@Autowired
	private DoctorDataRepository doctorRepository;

	@Autowired
	private BillDataRepository billRepository;

	@Override
	public TreatmentData addTreatmentDetails(TreatmentData treatmentData) {
		PatientData patientData = patientRepository
				.findByIdAndIsDeleted(treatmentData.getPatientData().getId(), Constant.IS_DELETED_FALSE)
				.orElseThrow(() -> new ResourceNotFoundException("Patient id not found "));

		Optional<TreatmentData> existData = treatmentRepository
				.findByPatientDataId(treatmentData.getPatientData().getId());
		if (existData.isPresent()) {
			throw new DataAlreadyExistException("patient id  already exist");
		}

		DoctorData doctorData = doctorRepository
				.findByIdAndIsDeletedAndIsWorking(treatmentData.getDoctorData().getId(), Constant.IS_DELETED_FALSE,
						Constant.status_Working)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor is not working "));

		treatmentData.setPatientData(patientData);
		treatmentData.setDoctorData(doctorData);

		TreatmentData data2 = treatmentRepository.save(treatmentData);
		return data2;
	}

	@Override
	public TreatmentData updateCheckin(Long id, TreatmentData treatmentData) {

		TreatmentData treatmentData2 = treatmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Treatment Id not found"));
		treatmentData2.setCheckIn(treatmentData.getCheckIn());
		treatmentData2.setUpdatedOn(new Date());
		return treatmentRepository.save(treatmentData2);
	}

	@Override
	public TreatmentData updateCheckout(Long id) {
		TreatmentData treatmentData = treatmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Treatment Id not found"));
		if (treatmentData.getCheckOut() == null) {
			treatmentData.setCheckOut(new Date());
			treatmentData.setUpdatedOn(new Date());
			treatmentData = generatePatientBill(treatmentData);
		} else {
			throw new AlredyCheckOutException("Patient Alredy CheckOut");

		}
		return treatmentRepository.save(treatmentData);

	}

	private TreatmentData generatePatientBill(TreatmentData treatmentData) {

		BillData billData = new BillData();

		DoctorData doctorData = doctorRepository
				.findByIdAndIsDeleted(treatmentData.getDoctorData().getId(), Constant.IS_DELETED_FALSE)
				.orElseThrow(() -> new ResourceNotFoundException("doctor id not found "));

		billData.setTreatmentId(treatmentData);

		if (treatmentData.getCheckIn() == null) {
			throw new ResourceNotFoundException("Checkin first");

		}

		long diffrentInTime = treatmentData.getCheckOut().getTime() - treatmentData.getCheckIn().getTime();

		long diffrentInDate = TimeUnit.MILLISECONDS.toDays(diffrentInTime);

		float doctorFee = doctorData.getFee() * diffrentInDate;

		float departmentFee = treatmentData.getDoctorData().getDepartment().getFee() * diffrentInDate;

		float dayCharge = treatmentData.getPer_Day_Charge() * diffrentInDate;

		float totalBill = doctorFee + departmentFee + dayCharge;

		billData.setStatus(Constant.BILL_UNPAID);
		billData.setTotal(totalBill);
		billData.setNoOfDaysAdmited(diffrentInDate);
		billData.setPerDayCharge(doctorData.getFee());

		billData = billRepository.save(billData);

		treatmentData.setBillId(billData.getId());
		return treatmentData;

	}

	@Override
	public TreatmentData getTreatmentById(long id) {
		TreatmentData treatmentData = treatmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("treatment id not found"));

		return treatmentData;
	}

	@Override
	public TreatmentData getSummaryDetails(long id) {
		PatientData patientData = patientRepository.findByIdAndIsDeleted(id, Constant.IS_DELETED_FALSE)
				.orElseThrow(() -> new ResourceNotFoundException("Patient id not found "));

		TreatmentData treatmentData = treatmentRepository.findByPatientData(patientData);
		return treatmentData;
	}

}
