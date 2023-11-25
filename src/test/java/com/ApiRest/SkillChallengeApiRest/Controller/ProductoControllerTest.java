package com.ApiRest.SkillChallengeApiRest.Controller;

import com.ApiRest.SkillChallengeApiRest.controller.ProductoController;
import com.ApiRest.SkillChallengeApiRest.entity.Producto;
import com.ApiRest.SkillChallengeApiRest.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.BDDMockito.given;
@WebMvcTest(ProductoController.class)

public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Test
    public void getProductoSuccess() throws Exception{
        Long productoId = 1L;
        Producto producto = new Producto();
        producto.setIdProducto(productoId);

        given(productoService.getProducto(productoId)).willReturn(producto);

        mockMvc.perform(MockMvcRequestBuilders.get("/productos/{id}", productoId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getProductFailed() throws Exception{
        Long productoId = 0L;
        given(productoService.getProducto(productoId)).willReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/productos/{id}", productoId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createProductoFailed() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/productos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createProductoSuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/productos/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"string\",\"descripcion\":\"string\",\"precioUnitario\": 1,\"categoria\":\"string\",\"estatus\": 0,\"detallesEspecificos\":\"string\",\"vendedor_id\": 1}"))
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }
}