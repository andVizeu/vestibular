package br.com.vestibular.integrationtests;

import br.com.vestibular.api.entrypoint.rest.vestibular.requests.CreateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.requests.UpdateVestibularDTO;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularResponse;
import br.com.vestibular.api.entrypoint.rest.vestibular.responses.VestibularesResponse;
import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.integrationtests.config.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.vestibular.integrationtests.CreateDtoUtils.createVestibularDTO;
import static br.com.vestibular.integrationtests.CreateDtoUtils.updateVestibularDTO;
import static br.com.vestibular.integrationtests.CreateEntityUtils.createVestibular;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class VestibularIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private VestibularGateway gateway;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void shouldCreateVestibularTest() throws Exception {
        final CreateVestibularDTO dto = createVestibularDTO();

        mvc.perform(post("/vestibulares")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vestibularUUID").isNotEmpty())
                .andExpect(jsonPath("$.dataInicio").isNotEmpty())
                .andExpect(jsonPath("$.dataFim").isNotEmpty())
                .andExpect(jsonPath("$.cursos").isEmpty())
                .andExpect(jsonPath("$.salas").isEmpty())
                .andExpect(jsonPath("$.candidatos").isEmpty());
    }

    @Test
    void shouldListVestibularTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        gateway.addVestibular(vestibular);

        final ResultActions result = mvc.perform(get("/vestibulares")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vestibulares").isNotEmpty());

        final String content = result.andReturn().getResponse().getContentAsString();
        final VestibularesResponse vestibulares = objectMapper.readValue(content, VestibularesResponse.class);

        assertTrue(vestibulares.getVestibulares().size() > 0);
    }

    @Test
    void shouldGetVestibularTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final  Vestibular savedVestibular = gateway.addVestibular(vestibular);

        mvc.perform(get("/vestibulares/{vestibularUUID}",
                        savedVestibular.getVestibularUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vestibularUUID").value(savedVestibular.getVestibularUUID().toString()))
                .andExpect(jsonPath("$.dataInicio").isNotEmpty())
                .andExpect(jsonPath("$.dataFim").isNotEmpty())
                .andExpect(jsonPath("$.cursos").isEmpty())
                .andExpect(jsonPath("$.salas").isEmpty())
                .andExpect(jsonPath("$.candidatos").isEmpty());
    }

    @Test
    void shouldUpdateVestibularTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final  Vestibular savedVestibular = gateway.addVestibular(vestibular);

        final UpdateVestibularDTO dto = updateVestibularDTO();

        final ResultActions result = mvc.perform(patch("/vestibulares/{vestibularUUID}",
                        savedVestibular.getVestibularUUID())
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vestibularUUID").isNotEmpty())
                .andExpect(jsonPath("$.dataInicio").isNotEmpty())
                .andExpect(jsonPath("$.dataFim").isNotEmpty())
                .andExpect(jsonPath("$.cursos").isEmpty())
                .andExpect(jsonPath("$.salas").isEmpty())
                .andExpect(jsonPath("$.candidatos").isEmpty());

        final String content = result.andReturn().getResponse().getContentAsString();
        final VestibularResponse response = objectMapper.readValue(content, VestibularResponse.class);
        assertNotEquals(vestibular.getDataInicio(), response.getDataInicio());
        assertNotEquals(vestibular.getDataFim(), response.getDataFim());
    }

    @Test
    void shouldDeleteVestibularTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final  Vestibular savedVestibular = gateway.addVestibular(vestibular);

        final ResultActions result = mvc.perform(delete("/vestibulares/{vestibularUUID}",
                        savedVestibular.getVestibularUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        final String content = result.andReturn().getResponse().getContentAsString();
        final VestibularesResponse vestibulares = objectMapper.readValue(content, VestibularesResponse.class);

        assertFalse(vestibulares.getVestibulares().stream().anyMatch(vestibularEntity ->
                vestibularEntity.getVestibularUUID().equals(savedVestibular.getVestibularUUID())));
    }

}
