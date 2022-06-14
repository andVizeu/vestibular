package br.com.vestibular.integrationtests;

import br.com.vestibular.api.entrypoint.rest.curso.requests.CreateCursoDTO;
import br.com.vestibular.api.entrypoint.rest.curso.requests.UpdateCursoDTO;
import br.com.vestibular.api.entrypoint.rest.curso.responses.CursosResponse;
import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.core.gateway.CursoGateway;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.integrationtests.config.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static br.com.vestibular.integrationtests.CreateDtoUtils.createCursoDTO;
import static br.com.vestibular.integrationtests.CreateDtoUtils.updateCursoDTO;
import static br.com.vestibular.integrationtests.CreateEntityUtils.createCurso;
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
class CursoIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private VestibularGateway vestibularGateway;

    @Autowired
    private CursoGateway cursoGateway;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCursoTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final  Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);

        final CreateCursoDTO dto = createCursoDTO();

        final ResultActions result = mvc.perform(post("/vestibulares/{vestibularUUID}/cursos",
                        savedVestibular.getVestibularUUID())
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cursos").isNotEmpty());

        final String content = result.andReturn().getResponse().getContentAsString();
        final CursosResponse cursos = objectMapper.readValue(content, CursosResponse.class);

        assertTrue(cursos.getCursos().size() > 0);
        assertEquals(cursos.getCursos().get(0).getNome(), dto.getNome());
        assertTrue(nonNull(cursos.getCursos().get(0).getCursoUUID()));
    }

    @Test
    void shouldListCursoTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        cursoGateway.addCurso(createCurso(), savedVestibular.getVestibularUUID()).get(0);

        final ResultActions result = mvc.perform(get("/vestibulares/{vestibularUUID}/cursos",
                        savedVestibular.getVestibularUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cursos").isNotEmpty());

        final String content = result.andReturn().getResponse().getContentAsString();
        final CursosResponse cursos = objectMapper.readValue(content, CursosResponse.class);

        assertTrue(cursos.getCursos().size() > 0);
    }

    @Test
    void shouldGetCursoTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Curso savedCurso = cursoGateway.addCurso(createCurso(), savedVestibular.getVestibularUUID()).get(0);

        mvc.perform(get("/vestibulares/{vestibularUUID}/cursos/{cursoUUID}",
                        savedVestibular.getVestibularUUID(), savedCurso.getCursoUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(savedCurso.getNome()));
    }

    @Test
    void shouldUpdateCursoTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Curso savedCurso = cursoGateway.addCurso(createCurso(), savedVestibular.getVestibularUUID()).get(0);

        final UpdateCursoDTO dto = updateCursoDTO();

        mvc.perform(patch("/vestibulares/{vestibularUUID}/cursos/{cursoUUID}",
                        savedVestibular.getVestibularUUID(), savedCurso.getCursoUUID())
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(dto.getNome()));
    }

    @Test
    void shouldDeleteCursoTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Curso savedCurso = cursoGateway.addCurso(createCurso(), savedVestibular.getVestibularUUID()).get(0);

        mvc.perform(delete("/vestibulares/{vestibularUUID}/cursos/{cursoUUID}",
                        savedVestibular.getVestibularUUID(), savedCurso.getCursoUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cursos").isEmpty());
    }

}
