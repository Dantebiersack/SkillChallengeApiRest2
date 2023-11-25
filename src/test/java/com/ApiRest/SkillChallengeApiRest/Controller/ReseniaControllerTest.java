package com.ApiRest.SkillChallengeApiRest.Controller;

import com.ApiRest.SkillChallengeApiRest.controller.ReseniaController;
import com.ApiRest.SkillChallengeApiRest.entity.Resenia;
import com.ApiRest.SkillChallengeApiRest.service.ReseniaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;

@WebMvcTest(ReseniaController.class)
public class ReseniaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReseniaService reseniaService;

    @Test
    public void getReseniaSuccess() throws Exception{
        Long reseniaId = 1L;
        Resenia resenia = new Resenia();
        resenia.setIdResenia(reseniaId);

        given(reseniaService.getResenia(reseniaId)).willReturn(resenia);

        mockMvc.perform(MockMvcRequestBuilders.get("/resenias/{id}", reseniaId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getReseniaFailed() throws Exception{
        Long reseniaId = 0L;
        given(reseniaService.getResenia(reseniaId)).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/resenias/{id}", reseniaId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createReseniaFailed() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/resenias/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void createReseniaSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/resenias/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"contenido\": \"string\",\n" +
                                "  \"puntuacion\": 1,\n" +
                                "  \"estatus\": 0,\n" +
                                "  \"comprador_id\": 1\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
