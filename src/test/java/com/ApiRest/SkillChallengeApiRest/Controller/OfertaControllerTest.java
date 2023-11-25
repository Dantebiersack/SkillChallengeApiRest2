package com.ApiRest.SkillChallengeApiRest.Controller;

import com.ApiRest.SkillChallengeApiRest.controller.OfertaController;
import com.ApiRest.SkillChallengeApiRest.entity.Oferta;
import com.ApiRest.SkillChallengeApiRest.service.OfertaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;

@WebMvcTest(OfertaController.class)
public class OfertaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OfertaService ofertaService;

    @Test
    public void getOfertaSuccess() throws Exception{
        Long ofertaId = 1L;
        Oferta oferta = new Oferta();
        oferta.setIdOferta(ofertaId);

        given(ofertaService.getOferta(ofertaId)).willReturn(oferta);

        mockMvc.perform(MockMvcRequestBuilders.get("/ofertas/{id}", ofertaId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getOfertaFailed() throws Exception{
        Long ofertaId = 0L;
        given(ofertaService.getOferta(ofertaId)).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/ofertas/{id}", ofertaId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createOfertaFailed() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/ofertas/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void createOfertaSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/ofertas/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"descuento\": 1,\n" +
                                "  \"fechaValidez\": \"2023-11-25T02:15:51.762Z\",\n" +
                                "  \"producto_id\": 2\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
