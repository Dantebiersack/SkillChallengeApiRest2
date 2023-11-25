package com.ApiRest.SkillChallengeApiRest.Controller;

import com.ApiRest.SkillChallengeApiRest.controller.VendedorController;
import com.ApiRest.SkillChallengeApiRest.entity.Vendedor;
import com.ApiRest.SkillChallengeApiRest.service.VendedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;

@WebMvcTest(VendedorController.class)
public class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendedorService vendedorService;

    @Test
    public void getVendedorSuccess() throws Exception{
        Long vendedorId = 1L;
        Vendedor vendedor = new Vendedor();
        vendedor.setIdVendedor(vendedorId);

        given(vendedorService.getVendedor(vendedorId)).willReturn(vendedor);

        mockMvc.perform(MockMvcRequestBuilders.get("/vendedores/{id}", vendedorId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getVendedorFailed() throws Exception{
        Long vendedorId = 0L;
        given(vendedorService.getVendedor(vendedorId)).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/vendedores/{id}", vendedorId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createVendedorFailed() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/vendedores/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void createVendedorSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/vendedores/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"nombre\": \"string\",\n" +
                                "  \"email\": \"string\",\n" +
                                "  \"contrasenia\": \"string\",\n" +
                                "  \"estatus\": 0\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
