package br.com.vestibular.integrationtests;

import br.com.vestibular.api.entrypoint.rest.candidato.requests.CreateCandidatoDTO;
import br.com.vestibular.api.entrypoint.rest.candidato.requests.UpdateCandidatoDTO;
import br.com.vestibular.api.entrypoint.rest.candidato.responses.CandidatosResponse;
import br.com.vestibular.core.domain.Candidato;
import br.com.vestibular.core.domain.Curso;
import br.com.vestibular.core.domain.Vestibular;
import br.com.vestibular.core.gateway.CandidatoGateway;
import br.com.vestibular.core.gateway.CursoGateway;
import br.com.vestibular.core.gateway.SalaGateway;
import br.com.vestibular.core.gateway.VestibularGateway;
import br.com.vestibular.integrationtests.config.IntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static br.com.vestibular.integrationtests.CreateDtoUtils.createCandidatoDTO;
import static br.com.vestibular.integrationtests.CreateDtoUtils.updateCandidatoDTO;
import static br.com.vestibular.integrationtests.CreateEntityUtils.createCandidato;
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
class CandidatoIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private VestibularGateway vestibularGateway;

    @Autowired
    private CursoGateway cursoGateway;

    @Autowired
    private CandidatoGateway candidatoGateway;

    @Autowired
    private SalaGateway salaGateway;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCandidatoTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Curso curso = cursoGateway.addCurso(createCurso(), savedVestibular.getVestibularUUID()).get(0);

        final CreateCandidatoDTO dto = createCandidatoDTO();

        final ResultActions result = mvc.perform(post("/vestibulares/{vestibularUUID}/cursos/{cursoUUID}/candidatos",
                        savedVestibular.getVestibularUUID(), curso.getCursoUUID())
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.candidatos").isNotEmpty());

        final String content = result.andReturn().getResponse().getContentAsString();
        final CandidatosResponse candidatos = objectMapper.readValue(content, CandidatosResponse.class);

        assertTrue(candidatos.getCandidatos().size() > 0);
        assertTrue(nonNull(candidatos.getCandidatos().get(0).getVestibular()));
        assertTrue(nonNull(candidatos.getCandidatos().get(0).getId()));
        assertEquals(candidatos.getCandidatos().get(0).getNome(), dto.getNome());
        assertEquals(candidatos.getCandidatos().get(0).getDataNascimento(), dto.getDataNascimento());
        assertEquals(candidatos.getCandidatos().get(0).getCpf(), dto.getCpf());
    }

    @Test
    void shouldAtribuirCadidatoSalaTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Curso savedCurso = cursoGateway.addCurso(createCurso(), savedVestibular.getVestibularUUID()).get(0);
        candidatoGateway.addCandidato(savedVestibular.getVestibularUUID(),
                savedCurso.getCursoUUID(), createCandidato(savedVestibular, savedCurso));

        mvc.perform(get("/vestibulares/{vestibularUUID}/assing",
                        savedVestibular.getVestibularUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldListCandidatosTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Curso savedCurso = cursoGateway.addCurso(createCurso(), savedVestibular.getVestibularUUID()).get(0);
        candidatoGateway.addCandidato(savedVestibular.getVestibularUUID(),
                savedCurso.getCursoUUID(), createCandidato(savedVestibular, savedCurso));

        final ResultActions result = mvc.perform(get("/vestibulares/{vestibularUUID}/candidatos?cursoUUID={cursoUUID}",
                        savedVestibular.getVestibularUUID(), savedCurso.getCursoUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.candidatos").isNotEmpty());

        final String content = result.andReturn().getResponse().getContentAsString();
        final CandidatosResponse salas = objectMapper.readValue(content, CandidatosResponse.class);

        assertTrue(salas.getCandidatos().size() > 0);
    }

    @Test
    void shouldGetCandidatoTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Curso savedCurso = cursoGateway.addCurso(createCurso(), savedVestibular.getVestibularUUID()).get(0);
        final List<Candidato> candidatos = candidatoGateway.addCandidato(savedVestibular.getVestibularUUID(),
                savedCurso.getCursoUUID(), createCandidato(savedVestibular, savedCurso));

        mvc.perform(get("/vestibulares/{vestibularUUID}/candidatos/{candidatoId}",
                        savedVestibular.getVestibularUUID(), candidatos.get(0).getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(candidatos.get(0).getId()))
                .andExpect(jsonPath("$.vestibular").isNotEmpty())
                .andExpect(jsonPath("$.nome").value(candidatos.get(0).getNome()))
                .andExpect(jsonPath("$.dataNascimento").isNotEmpty())
                .andExpect(jsonPath("$.cpf").value(candidatos.get(0).getCpf()))
                .andExpect(jsonPath("$.curso").isNotEmpty());
    }

    @Test
    void shouldUpdateCandidatoTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Curso savedCurso = cursoGateway.addCurso(createCurso(), savedVestibular.getVestibularUUID()).get(0);
        final List<Candidato> candidatos = candidatoGateway.addCandidato(savedVestibular.getVestibularUUID(),
                savedCurso.getCursoUUID(), createCandidato(savedVestibular, savedCurso));

        final UpdateCandidatoDTO dto = updateCandidatoDTO();

        mvc.perform(patch("/vestibulares/{vestibularUUID}/cursos/{cursoUUID}/candidatos/{candidatoId}",
                        savedVestibular.getVestibularUUID(), savedCurso.getCursoUUID(), candidatos.get(0).getId())
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(candidatos.get(0).getId()))
                .andExpect(jsonPath("$.vestibular").isNotEmpty())
                .andExpect(jsonPath("$.nome").value(dto.getNome()))
                .andExpect(jsonPath("$.dataNascimento").isNotEmpty())
                .andExpect(jsonPath("$.cpf").value(dto.getCpf()))
                .andExpect(jsonPath("$.curso").isNotEmpty());
    }

    @Test
    void shouldDeleteCandidatoTest() throws Exception {
        final Vestibular vestibular = createVestibular();
        final Vestibular savedVestibular = vestibularGateway.addVestibular(vestibular);
        final Curso savedCurso = cursoGateway.addCurso(createCurso(), savedVestibular.getVestibularUUID()).get(0);
        final List<Candidato> candidatos = candidatoGateway.addCandidato(savedVestibular.getVestibularUUID(),
                savedCurso.getCursoUUID(), createCandidato(savedVestibular, savedCurso));

        mvc.perform(delete("/vestibulares/{vestibularUUID}/cursos/{cursoUUID}/candidatos/{candidatoId}",
                        savedVestibular.getVestibularUUID(), savedCurso.getCursoUUID(), candidatos.get(0).getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
