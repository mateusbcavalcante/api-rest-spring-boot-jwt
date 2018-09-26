package com.movie.api.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.requests.AuthenticationRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

	protected MediaType contentType = MediaType.APPLICATION_JSON;
	
	@Autowired
	protected MockMvc mvc;

    @Test
    public void unauthorizedAuthenticationWithAnonymousUser() throws Exception {

        AuthenticationRequest jwtAuthenticationRequest = new AuthenticationRequest("user", "password");

        mvc.perform(post("/auth")
            .contentType(contentType)
            .content(new ObjectMapper().writeValueAsString(jwtAuthenticationRequest)))
        	.andExpect(status().isBadRequest());
    }
    
    @Test
    public void successfulAuthenticationWithAnonymousUser() throws Exception {

        AuthenticationRequest jwtAuthenticationRequest = new AuthenticationRequest("admin", "admin");

        mvc.perform(post("/auth")
            .contentType(contentType)
            .content(new ObjectMapper().writeValueAsString(jwtAuthenticationRequest)))
        	.andExpect(status().isOk());
    }
}