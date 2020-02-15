package com.nit.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LibMembership {
	private int id;
	private Date joiningDate;
	private Student student;
	
	@Override
	public String toString() {
		return "LibMembership [id=" + id + ", joiningDate=" + joiningDate + "]";
	}
}
