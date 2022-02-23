package com.terrapay.hm.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "treatment")
public class TreatmentData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "patient_id")
	private PatientData patientData;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "doctor_id")
	private DoctorData doctorData;
	
	@Column(name = "per_day_charge")
	private float per_Day_Charge;

	@Column(name = "bill_id")
	private long billId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_on")
	private Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_on")
	private Date updatedOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "checkin")
	private Date checkIn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "checkOut")
	private Date checkOut;


}