package com.ApiRest.SkillChallengeApiRest.Controller;

import com.ApiRest.SkillChallengeApiRest.controller.TransaccionController;
import com.ApiRest.SkillChallengeApiRest.entity.Transaccion;
import com.ApiRest.SkillChallengeApiRest.service.TransaccionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;

@WebMvcTest(TransaccionController.class)
public class TransaccionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransaccionService transaccionService;

    @Test
    public void getTransaccionSuccess() throws Exception{
        Long transaccionId = 1L;
        Transaccion transaccion = new Transaccion();
        transaccion.setIdTransaccion(transaccionId);

        given(transaccionService.getTransaccion(transaccionId)).willReturn(transaccion);

        mockMvc.perform(MockMvcRequestBuilders.get("/transacciones/{id}", transaccionId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getTransaccionFailed() throws Exception{
        Long transaccionId = 0L;
        given(transaccionService.getTransaccion(transaccionId)).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/transacciones/{id}", transaccionId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createTransaccionFailed() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/transacciones/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createTransaccionSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/transacciones/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"montoTotal\": 1,\n" +
                                "  \"paisEnvio\": \"string\",\n" +
                                "  \"estadoEnvio\": \"string\",\n" +
                                "  \"calleEnvio\": \"string\",\n" +
                                "  \"coloniaEnvio\": \"string\",\n" +
                                "  \"numEnvio\": \"string\",\n" +
                                "  \"fechaEnvio\": \"2023-11-25T02:50:32.235Z\",\n" +
                                "  \"metodoPago\": \"string\",\n" +
                                "  \"noTarjeta\": \"string\",\n" +
                                "  \"estatus\": 0,\n" +
                                "  \"comprador_id\": 0\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
