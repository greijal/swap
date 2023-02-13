package com.swap.service.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.swap.service.dto.RepositoryRequest;
import com.swap.service.service.RepositoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GitController.class)
class GitControllerTest {

    @MockBean
    private RepositoryService repositoryService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test post request info")
    void infoTest() throws Exception {

        var request = new RepositoryRequest();
        request.setOwner("owner");
        request.setRepository("repo");

        when(repositoryService.exist("owner", "repo")).thenReturn(true);

        mockMvc.perform(post("/repository")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test post request info and repo not exist")
    void infoRepoNotFoundTest() throws Exception {

        var request = new RepositoryRequest();
        request.setOwner("owner");
        request.setRepository("repo");

        when(repositoryService.exist("owner", "repo")).thenReturn(false);

        mockMvc.perform(post("/repository")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvSource({
            ",",
            "owner,",
            ", repo",
            " , ",
    })
    @DisplayName("Test post request invalid")
    void infoInvalidRequestTest(String owner, String repo) throws Exception {

        var request = new RepositoryRequest();
        request.setOwner(owner);
        request.setRepository(repo);

        when(repositoryService.exist("owner", "repo")).thenReturn(false);

        mockMvc.perform(post("/repository")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}