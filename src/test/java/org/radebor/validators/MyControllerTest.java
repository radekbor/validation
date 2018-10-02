package org.radebor.validators;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
    }

    @Test
    public void shouldThrowErrorInEn() throws Exception {
        Map<String, String> body = new HashMap<>();
        body.put("id", "123");
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                post("/handle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "en")
        ).andDo(print())
                .andExpect(jsonPath("$.restPojo").value("ID should be longer"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldThrowErrorInPL() throws Exception {
        Map<String, String> body = new HashMap<>();
        body.put("id", "123");
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                post("/handle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "pl")
        ).andDo(print())
                .andExpect(jsonPath("$.restPojo").value("ID powinno byc dluzsze"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldThrowErrorInF() throws Exception {
        Map<String, String> body = new HashMap<>();
        body.put("id", "123");
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                post("/handle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "fr")
        ).andDo(print())
                .andExpect(jsonPath("$.restPojo").value("ID should be longer"))
                .andExpect(status().is4xxClientError());
    }
}
