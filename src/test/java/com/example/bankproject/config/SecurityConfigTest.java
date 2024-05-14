package com.example.bankproject.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class SecurityConfigTest {

    @Autowired
    private MockMvc mvc;


    @Test
    void authentication_test() throws Exception {

        ResultActions resultActions = mvc.perform(get("/api/s/hello"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        int httpStatusCode = resultActions.andReturn().getResponse().getStatus();
        System.out.println("테스트 :  " + responseBody);
        System.out.println("테스트 :  " + httpStatusCode);

        assertThat(httpStatusCode).isEqualTo(401);
    }


    @Test
    void authorization_test() throws Exception {
        ResultActions resultActions = mvc.perform(get("/api/admin/hello"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        int httpStatusCode = resultActions.andReturn().getResponse().getStatus();
        System.out.println("테스트 :  " + responseBody);
        System.out.println("테스트 :  " + httpStatusCode);

        assertThat(httpStatusCode).isEqualTo(401);
    }




}