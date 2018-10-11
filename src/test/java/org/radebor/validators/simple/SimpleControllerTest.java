package org.radebor.validators.simple;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SimpleControllerTest {

    private static final String SHORT_ID = "123";

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
    }

    @Test
    public void shouldThrowErrorInEn() throws Exception {
        sendRequest(createBody(SHORT_ID), "en")
                .andExpect(jsonPath("$.restPojo").value("SHORT_ID should be longer"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldThrowErrorInPL() throws Exception {
        sendRequest(createBody("123"), "pl")
                .andExpect(jsonPath("$.restPojo").value("SHORT_ID powinno byc dluzsze"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void shouldThrowErrorInF() throws Exception {
        sendRequest(createBody("123"), "fr")
                .andExpect(jsonPath("$.restPojo").value("SHORT_ID should be longer"))
                .andExpect(status().is4xxClientError());
    }

    private Map<String, String> createBody(String id) {
        Map<String, String> body = new HashMap<>();
        body.put("id", id);
        return body;
    }

    private ResultActions sendRequest(Map<String, String> body, String lang) throws Exception {
        return mockMvc.perform(
                post("/handle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .header(HttpHeaders.ACCEPT_LANGUAGE, lang)
        ).andDo(print());
    }
}
