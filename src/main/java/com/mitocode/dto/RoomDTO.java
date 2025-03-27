package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {

	private Integer idRoom;

	@NotNull
	@Min(value = 1)
	private int numberRoom;

	@NotNull
	@Size(min = 1, max = 25)
	private String typeRoom;

	@NotNull
	@Min(value = 1)
	private Double priceRoom;

	@NotNull
	private boolean available;
}
