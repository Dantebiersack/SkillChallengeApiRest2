package com.ApiRest.SkillChallengeApiRest.Controller;

import com.ApiRest.SkillChallengeApiRest.controller.CarritoController;
import com.ApiRest.SkillChallengeApiRest.entity.Carrito;
import com.ApiRest.SkillChallengeApiRest.service.CarritoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;

@WebMvcTest(CarritoController.class)
public class CarritoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarritoService carritoService;

    @Test
    public void getCarritoSuccess() throws Exception{
        Long carritoId = 1L;
        Carrito carrito = new Carrito();
        carrito.setIdCarrito(carritoId);

        given(carritoService.getCarrito(carritoId)).willReturn(carrito);

        mockMvc.perform(MockMvcRequestBuilders.get("/carritos/{id}", carritoId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCarritoFailed() throws Exception{
        Long carritoId = 0L;
        given(carritoService.getCarrito(carritoId)).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/carritos/{id}", carritoId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createCarritoFailed() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/carritos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"cantidad\": 0,\n" +
                                "  \"producto_id\": 0,\n" +
                                "  \"comprador_id\": 0\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void createCarritoSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/carritos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"cantidad\": 1,\n" +
                                "  \"producto_id\": 1,\n" +
                                "  \"comprador_id\": 1\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
