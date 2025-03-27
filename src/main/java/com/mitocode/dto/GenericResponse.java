package com.mitocode.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record GenericResponse<T>(
        int status,
        String message,
        List<T> data
) {
}
