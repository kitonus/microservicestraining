package com.jatis.training.classroom.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.jatis.training.classroom.util.SessionUtil;

@Entity
public class ClassroomEntity {

	@Id
	@GeneratedValue
	private UUID id;
	
	private String name;
	
	private int grade;
	
	private String teacher;
	
	private String updatedBy;
	
	private Date updateDateTime;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	
	@PrePersist
	@PreUpdate
	private void onSave() {
		this.updatedBy = String.valueOf(SessionUtil.getSessionAttribute("username"));
		this.updateDateTime = new Date();
	}
}
