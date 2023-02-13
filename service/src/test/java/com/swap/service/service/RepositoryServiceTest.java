package com.swap.service.service;

import com.swap.client.GitHubClient;
import com.swap.client.dto.Repository;
import com.swap.dto.RepositoryMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RepositoryServiceTest {

    @Mock
    private GitHubClient gitHubClient;
    @Mock
    private KafkaTemplate<String, RepositoryMessage> kafkaTemplate;
    @InjectMocks
    private RepositoryService repositoryService;

    @Test
    @DisplayName("Repository exist")
    void existTest() {
        var repo = mock(Repository.class);
        when(gitHubClient.getRepository("owner", "repo")).thenReturn(repo);
        var result = repositoryService.exist("owner", "repo");
        assertTrue(result);
    }

    @Test
    @DisplayName("Repository no exist")
    void existNotTest() {
        when(gitHubClient.getRepository("owner", "repo")).thenReturn(null);
        var result = repositoryService.exist("owner", "repo");
        assertFalse(result);
    }
}