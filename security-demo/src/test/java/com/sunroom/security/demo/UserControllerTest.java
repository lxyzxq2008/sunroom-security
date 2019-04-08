package com.sunroom.security.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception{
        String result = mockMvc.perform(get("/user")
                .param("username", "sunroom")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
    @Test
    public void whenQuerySuccess2() throws Exception{
        String result = mockMvc.perform(get("/user/user2")
                .param("username", "sunroom")
                .param("age", "18")
                .param("ageTo", "60")
                .param("xxx", "yyy")
//                .param("size", "15")
//                .param("page", "3")
//                .param("sort", "age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetUserInfoSuccess() throws Exception{
        String result = mockMvc.perform(get("/user/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("sunroom"))
        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetUserInfoFail() throws Exception {
        mockMvc.perform(get("/user/a")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        Long dateTime = c.getTimeInMillis();
        String content = "{\"username\":\"sunroom\",\"password\":null,\"birthday\":"+dateTime+"}";
        System.out.println(content);
        String result = mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        Long dateTime = LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        String content = "{\"id\":\"1\",\"username\":\"sunroom\",\"password\":null,\"birthday\":"+dateTime+"}";
        System.out.println(content);
        String result = mockMvc.perform(put("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        String result = mockMvc.perform(delete("/user/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(result);
    }
}
