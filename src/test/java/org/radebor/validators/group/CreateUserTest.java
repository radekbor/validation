package org.radebor.validators.group;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CreateUserTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldPassWhenFianndValidSSN() throws Exception {
        sendRequest(createRequest("FI", "010236-987B"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldPassWhenUSValidSSN() throws Exception {
        sendRequest(createRequest("US", "123-45-6789"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldThrowErrorWhenFIAndWrongSSN() throws Exception {
        sendRequest(createRequest("FI", "!10236-987B"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.userRequest").value("invalid.ssn.fi"));
    }


    @Test
    public void shouldThrowErrorWhenUSAndWrongSSN() throws Exception {
        sendRequest(createRequest("US", "010236-987B"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.userRequest").value("invalid.ssn.us"));

    }

    private Map<String, String> createRequest(String us, String s) {
        Map<String, String> body = new HashMap<>();
        body.put("country", us);
        body.put("ssn", s);
        return body;
    }

    private ResultActions sendRequest(Map<String, String> body) throws Exception {
        return mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body))
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "en")
        ).andDo(print());
    }
}
