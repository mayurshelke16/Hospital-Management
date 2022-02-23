package com.terrapay.hm.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "department")
public class DepartmentData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = " Department name is mandatory")
	private String name;
	private float fee;
	
	@Column(name = "is_deleted")
	private boolean isDeleted;


	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createOn")
	private Date createOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateOn")
	private Date updatedOn;
	 
	
}
