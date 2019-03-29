package com.pharmacy.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.auth.controllers.UserController;
import com.pharmacy.auth.entities.UserEntity;
import com.pharmacy.auth.models.User;
import com.pharmacy.auth.repositories.UserJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class TestUserController {

    @MockBean
    @Qualifier("userJpaRepository")
    private UserJpaRepository userJpaRepository;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void loginTest() throws Exception {
        UserEntity userEntity = new UserEntity("prueba@pruebamail.com", "$2a$10$z4HSwr1bqfruCWkkIkyXruFLT6houJjnpHrNFKOP0thEYjrnhPq2W");
        when(userJpaRepository.findByUsername(any(String.class))).thenReturn(userEntity);

        User user = new User("prueba@pruebamail.com", "prueba", null);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .header("Origin","*")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(mapper.readValue(content, User.class).getUsername(), user.getUsername());
    }

}