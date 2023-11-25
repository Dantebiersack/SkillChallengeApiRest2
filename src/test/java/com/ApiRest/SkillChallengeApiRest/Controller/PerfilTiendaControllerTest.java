package com.ApiRest.SkillChallengeApiRest.Controller;

import com.ApiRest.SkillChallengeApiRest.controller.PerfilTiendaController;
import com.ApiRest.SkillChallengeApiRest.entity.PerfilTienda;
import com.ApiRest.SkillChallengeApiRest.service.PerfilTiendaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;

@WebMvcTest(PerfilTiendaController.class)
public class PerfilTiendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerfilTiendaService perfilTiendaService;

    @Test
    public void getPerfilTiendaSuccess() throws Exception{
        Long perfilTiendaId = 1L;
        PerfilTienda perfilTienda = new PerfilTienda();
        perfilTienda.setIdPerfilTienda(perfilTiendaId);

        given(perfilTiendaService.getPerfilTienda(perfilTiendaId)).willReturn(perfilTienda);

        mockMvc.perform(MockMvcRequestBuilders.get("/perfilTiendas/{id}", perfilTiendaId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getPerfilTiendaFailed() throws Exception{
        Long perfilTiendaId = 0L;
        given(perfilTiendaService.getPerfilTienda(perfilTiendaId)).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/perfilTiendas/{id}", perfilTiendaId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createPerfilTiendaFailed() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/perfilTiendas/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void createPerfilTiendaSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/perfilTiendas/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"nombreTienda\": \"string\",\n" +
                                "  \"descripcion\": \"string\",\n" +
                                "  \"telefono\": \"string\",\n" +
                                "  \"estatus\": 0,\n" +
                                "  \"vendedor_id\": 1\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
