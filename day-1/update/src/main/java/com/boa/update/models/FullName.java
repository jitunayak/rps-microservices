package com.boa.update.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullName {
	@Column(name="First_Name",nullable = false,length = 20)
	private String firstName;	
	
	@Column(name="Middle_Name",length = 20)
	private String middleName;
	
	@Column(name="Last_Name",nullable = false,length = 20)
	private String lastName;
}