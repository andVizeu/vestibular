package br.com.vestibular.integrationtests;

import br.com.vestibular.api.entrypoint.rest.sala.requests.CreateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.sala.requests.UpdateSalaDTO;
import br.com.vestibular.api.entrypoint.rest.sala.responses.SalasResponse;
import br.com.vestibular.core.domain.Sala;
import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.core.gateway.SalaGateway;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.integrationtests.config.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.vestibular.integrationtests.CreateDtoUtils.createSalaDTO;
import static br.com.vestibular.integrationtests.CreateDtoUtils.updateSalaDTO;
import static br.com.vestibular.integrationtests.CreateEntityUtils.createSala;
import static br.com.vestibular.integrationtests.CreateEntityUtils.createVestibular;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class SalaIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private VestibularGateway vestibularGateway;

    @Autowired
    private SalaGateway salaGateway;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateSalaTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final  Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);

        final CreateSalaDTO dto = createSalaDTO();

        final ResultActions result = mvc.perform(post("/vestibulares/{vestibularUUID}/salas",
                        savedVestibular.getVestibularUUID())
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salas").isNotEmpty());

        final String content = result.andReturn().getResponse().getContentAsString();
        final SalasResponse salas = objectMapper.readValue(content, SalasResponse.class);

        assertTrue(salas.getSalas().size() > 0);
        assertTrue(nonNull(salas.getSalas().get(0).getVestibular()));
        assertTrue(nonNull(salas.getSalas().get(0).getId()));
        assertEquals(salas.getSalas().get(0).getIdentificador(), dto.getIdentificador());
        assertEquals(salas.getSalas().get(0).getBloco(), dto.getBloco());
        assertEquals(salas.getSalas().get(0).getCapacidade(), dto.getCapacidade());
    }

    @Test
    void shouldListSalasTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        salaGateway.addSala(createSala(savedVestibular), savedVestibular.getVestibularUUID()).get(0);

        final ResultActions result = mvc.perform(get("/vestibulares/{vestibularUUID}/salas",
                        savedVestibular.getVestibularUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salas").isNotEmpty());

        final String content = result.andReturn().getResponse().getContentAsString();
        final SalasResponse salas = objectMapper.readValue(content, SalasResponse.class);

        assertTrue(salas.getSalas().size() > 0);
    }

    @Test
    void shouldGetSalaTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Sala savedSala = salaGateway.addSala(createSala(savedVestibular), savedVestibular.getVestibularUUID()).get(0);

        mvc.perform(get("/vestibulares/{vestibularUUID}/salas/{salaId}",
                        savedVestibular.getVestibularUUID(), savedSala.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedSala.getId()))
                .andExpect(jsonPath("$.identificador").value(savedSala.getIdentificador()))
                .andExpect(jsonPath("$.bloco").value(savedSala.getBloco()))
                .andExpect(jsonPath("$.capacidade").value(savedSala.getCapacidade()));
    }

    @Test
    void shouldUpdateSalaTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Sala savedSala = salaGateway.addSala(createSala(savedVestibular), savedVestibular.getVestibularUUID()).get(0);

        final UpdateSalaDTO dto = updateSalaDTO();

        mvc.perform(patch("/vestibulares/{vestibularUUID}/salas/{salaId}",
                        savedVestibular.getVestibularUUID(), savedSala.getId())
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.identificador").value(dto.getIdentificador()))
                .andExpect(jsonPath("$.bloco").value(dto.getBloco()))
                .andExpect(jsonPath("$.capacidade").value(dto.getCapacidade()));
    }

    @Test
    void shouldDeleteSalaTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Sala savedSala = salaGateway.addSala(createSala(savedVestibular), savedVestibular.getVestibularUUID()).get(0);

        mvc.perform(delete("/vestibulares/{vestibularUUID}/salas/{salaId}",
                        savedVestibular.getVestibularUUID(), savedSala.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salas").isEmpty());
    }

}
