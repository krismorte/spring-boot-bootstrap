package com.krismorte.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
@MappedSuperclass
@Data
public class MyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date createAt;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date updateAt;
	
	public MyEntity() {
		this.id = UUID.randomUUID().toString();
	}
	
	@PrePersist
	public void onPrePersist() {
		createAt = new Date();
	}

	@PreUpdate
	public void onPreUpdate() {
		updateAt = new Date();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		MyEntity identity = (MyEntity) o;
		return Objects.equals(id, identity.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
}
