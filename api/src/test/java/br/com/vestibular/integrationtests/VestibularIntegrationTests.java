package br.com.vestibular.integrationtests;

import br.com.vestibular.api.entrypoint.rest.vestibular.requests.CreateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularResponse;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularesResponse;
import br.com.vestibular.integrationtests.config.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.vestibular.integrationtests.CreateDtoUtils.createVestibularDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class VestibularIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static VestibularResponse vestibular;

    @Test
    @Order(1)
    void shouldCreateVestibularTest() throws Exception {
        final CreateVestibularDTO dto = createVestibularDto();

        final ResultActions result = mvc.perform(post("/vestibulares")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vestibularUUID").isNotEmpty())
                .andExpect(jsonPath("$.dataInicio").isNotEmpty())
                .andExpect(jsonPath("$.dataInicio").value(dto.getDataInicio().toString()))
                .andExpect(jsonPath("$.dataFim").isNotEmpty())
                .andExpect(jsonPath("$.dataFim").value(dto.getDataFim().toString()))
                .andExpect(jsonPath("$.cursos").isEmpty())
                .andExpect(jsonPath("$.salas").isEmpty())
                .andExpect(jsonPath("$.candidatos").isEmpty());

        final String content = result.andReturn().getResponse().getContentAsString();
        vestibular = objectMapper.readValue(content, VestibularResponse.class);
    }

    @Test
    @Order(2)
    void shouldListVestibularTest() throws Exception{
        final ResultActions result = mvc.perform(get("/vestibulares")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vestibulares").isNotEmpty());

        final String content = result.andReturn().getResponse().getContentAsString();
        final VestibularesResponse vestibulares = objectMapper.readValue(content, VestibularesResponse.class);

        assertEquals(vestibulares.getVestibulares().size(), 1);
    }

}
