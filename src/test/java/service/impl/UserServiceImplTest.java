package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Collective;
import model.CollectiveExternal;
import model.Root;
import model.User;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.RootHttpClient;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
//    private UserServiceImpl userService;
//    @Mock
//    private RootHttpClient httpClient;
//    @InjectMocks
//    private ObjectMapper objectMapper;
//    @BeforeEach
//    void setUp() {
//        userService = new UserServiceImpl();
//    }
//
//    @Test
//    void displayFilteredUsers() {
//        List<String> tags = List.of("azure-digital-twins",
//                "azure-secrets",
//                "azure-functions-docker",
//                "azure-cosmosdb-sqlapi",
//                "azure-ml-component");
//        Collective collective = new Collective(tags);
//        CollectiveExternal external = new CollectiveExternal(collective);
//        User user1 = new User(List.of(external), 45, 225, 567, 1,
//                "Moldova", "link", "link", "user1 name");
//        User user2 = new User(List.of(external), 35, 366, 781, 2,
//                "England", "link", "link", "user2 name");
//        Root root = new Root(List.of(user1, user2), false);
//
//        lenient().when(httpClient.get(any())).thenReturn(root);
//
//        userService.displayFilteredUsers(List.of("Moldova"), 223, 1,
//                List.of("java", "docker"));
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
}