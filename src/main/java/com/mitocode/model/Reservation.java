package com.mitocode.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idReservation;

	@ManyToOne
	@JoinColumn(name = "id_room", nullable = false, foreignKey = @ForeignKey(name = "FK_RESERVATION_ROOM"))
	private Room room;

	@Column(length = 50, nullable = false)
	private String customerName;

	@Column(nullable = false)
	private LocalDateTime checkInDate;

	@Column(nullable = false)
	private LocalDateTime checkOutDate;

}
