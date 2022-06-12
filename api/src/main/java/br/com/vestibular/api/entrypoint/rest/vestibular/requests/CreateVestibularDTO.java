package br.com.vestibular.api.entrypoint.rest.vestibular.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CreateVestibularDTO {
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
}
