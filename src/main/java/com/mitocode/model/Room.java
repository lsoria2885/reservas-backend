package com.mitocode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idRoom;

	@Column(nullable = false)
	private int numberRoom;

	@Column(length = 25, nullable = false)
	private String typeRoom;

	@Column(nullable = false, columnDefinition = "decimal(6,2)")
	private Double priceRoom;

	@Column(nullable = false)
	private boolean available;

}
