package com.terrapay.hm.entity;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class DoctorData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = " Name is mandatory")
	private String name;
	@NotBlank(message = " Name is mandatory")
	private String specialization;
	@Column(name = "is_Working")
	private String isWorking;
   	
   	@Column(name = "working_hours")
	private String workingHours;
	
   	private float fee;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_on;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated_on;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name= "dep_id")
	private DepartmentData department;
	

	
	
	
	
}
