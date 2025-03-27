package com.mitocode.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDTO {

	private Integer idReservation;

	@NotNull
	@JsonIncludeProperties(value = { "idRoom", "numberRoom", "typeRoom" })
	private RoomDTO room;

	@NotNull
	@Size(min = 1, max = 50)
	private String customerName;

	@NotNull
	private LocalDateTime checkInDate;

	@NotNull
	private LocalDateTime checkOutDate;

}
