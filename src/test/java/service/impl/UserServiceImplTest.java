package service.impl;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

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