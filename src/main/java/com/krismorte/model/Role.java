package com.krismorte.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Krisnamourt Filho (krisnamourt_ti@hotmail.com)
 */
@Entity
@Data
public class Role extends MyEntity {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(length=70)
	private String name;
	
}
