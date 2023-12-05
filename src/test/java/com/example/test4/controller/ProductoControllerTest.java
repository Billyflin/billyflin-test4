package com.example.test4.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCrearProductoCodigoInvalido() throws Exception {
        String productoStr = "s0412231144,Suzuki Vitara,14,9.900.000,16.827.524-1,proveedor@suzuki.com";

        mockMvc.perform(post("/crearProducto")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(productoStr))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCrearProductoNombreLargo() throws Exception {
        String productoStr = "S0412231144,Suzuki Vitara Suzuki Vitara Suzuki Vitara Suzuki Vitara,14,9.900.000,16.827.524-1,proveedor@suzuki.com";

        mockMvc.perform(post("/crearProducto")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(productoStr))
                .andExpect(status().isBadRequest());
    }
}
