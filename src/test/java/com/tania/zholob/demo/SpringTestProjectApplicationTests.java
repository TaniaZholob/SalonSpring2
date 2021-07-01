package com.tania.zholob.demo;

import com.tania.zholob.demo.controllers.Controller;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class SpringTestProjectApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Controller controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void test() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to studio of beauty")));
    }

    @Test
    void login() throws Exception{
        mockMvc.perform(get("/records"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void correctLogin () throws Exception{
        mockMvc.perform(formLogin().user("admin@gmail.com")
                .password("admin"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
