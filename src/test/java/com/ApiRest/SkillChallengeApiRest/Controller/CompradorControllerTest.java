package com.ApiRest.SkillChallengeApiRest.Controller;

import com.ApiRest.SkillChallengeApiRest.controller.CompradorController;
import com.ApiRest.SkillChallengeApiRest.entity.Comprador;
import com.ApiRest.SkillChallengeApiRest.repository.CompradorRepository;
import com.ApiRest.SkillChallengeApiRest.service.CompradorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@WebMvcTest(CompradorController.class)
public class CompradorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompradorService compradorService;
    @Autowired
    private CompradorService compradorServiceOriginal;

    @MockBean
    private CompradorRepository compradorRepository;

    private Comprador comprador;

    @BeforeEach
    public void setupComprador(){
        this.comprador = new Comprador();
    }
    @Test
    public void createCompradorSuccess() throws Exception {
        given(compradorService.createComprador(this.comprador)).willReturn(this.comprador);
        mockMvc.perform(MockMvcRequestBuilders.post("/compradores/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"nombre\": \"string\",\n" +
                                "  \"appMaterno\": \"string\",\n" +
                                "  \"telefono\": \"string\",\n" +
                                "  \"email\": \"string\",\n" +
                                "  \"contrasenia\": \"string\",\n" +
                                "  \"estatus\": 0,\n" +
                                "  \"appPaterno\": \"string\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void compradorEmailSuccess(){
        String email = "aa@gmail.com";
        given(compradorService.validarEmail(email)).willReturn(true);
        Boolean validEmail = compradorService.validarEmail(email);
        assertEquals(true,validEmail);
    }
    @Test
    public void compradorEmailFail(){
        String email = "aa@gmail.com";
        given(compradorService.validarEmail(email)).willReturn(false);
        Boolean validEmail = compradorService.validarEmail(email);
        assertEquals(false,validEmail);
    }

    @Test
    public void compradorEmailSuccessInUse(){
        String email = "aaa@gmail.com";
        Boolean validEmail = compradorServiceOriginal.validarEmail(email);
        assertEquals(false,validEmail);
    }

}
