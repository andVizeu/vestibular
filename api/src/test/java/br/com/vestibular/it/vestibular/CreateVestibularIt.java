package br.com.vestibular.it.vestibular;

import br.com.vestibular.api.entrypoint.rest.vestibular.requests.CreateVestibularDTO;
import br.com.vestibular.it.config.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.vestibular.it.CreateDtoUtils.createVestibularDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class CreateVestibularIt {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateVestibularTest() throws Exception {
        final CreateVestibularDTO dto = createVestibularDto();

        mvc.perform(post("/vestibulares")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vestibularUUID").exists())
                .andExpect(jsonPath("$.dataInicio").isNotEmpty())
                .andExpect(jsonPath("$.dataInicio").value(dto.getDataInicio().toString()))
                .andExpect(jsonPath("$.dataFim").isNotEmpty())
                .andExpect(jsonPath("$.dataFim").value(dto.getDataFim().toString()))
                .andExpect(jsonPath("$.cursos").isEmpty())
                .andExpect(jsonPath("$.salas").isEmpty())
                .andExpect(jsonPath("$.candidatos").isEmpty());
    }

}
