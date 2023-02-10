package service;

import client.UserHttpClient;
import model.Collective;
import model.CollectiveItem;
import model.ResponseWrapper;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import java.util.Set;

class UserServiceTest {
    private UserService userService;
    private UserHttpClient userHttpClient;

    @BeforeEach
    void setUp() {
        userHttpClient = Mockito.mock(UserHttpClient.class);
        userService = new UserService(userHttpClient);
    }

    @Test
    void displayFilteredUsers_twoValidUsers_printOnlyOne() {
        Set<String> tags = Set.of("azure-digital-twins",
                "azure-secrets",
                "azure-functions-docker",
                "azure-cosmosdb-sqlapi",
                "azure-ml-component");
        Collective collective = new Collective(tags);
        CollectiveItem collectiveItem = new CollectiveItem(collective);
        User user1 = new User(Set.of(collectiveItem), 45, 225, 567, 1,
                "Moldova", "link", "image", "user1 name");
        User user2 = new User(Set.of(collectiveItem), 35, 366, 781, 2,
                "England", "link", "image", "user2 name");
        ResponseWrapper wrapper = new ResponseWrapper(Set.of(user1, user2), false);

        when(userHttpClient.get(anyString())).thenReturn(wrapper);
        userService.displayFilteredUsers(Set.of("Moldova"), Set.of("java", "docker"), 223, 1);
        Mockito.verify(userHttpClient).get(anyString());
    }

    @Test
    void displayFilteredUsers_twoInvalidUsers_printNothing() {
        Set<String> tags = Set.of("azure-digital-twins",
                "azure-secrets",
                "azure-functions-docker",
                "azure-cosmosdb-sqlapi",
                "azure-ml-component");
        Collective collective = new Collective(tags);
        CollectiveItem collectiveItem = new CollectiveItem(collective);
        User user1 = new User(Set.of(collectiveItem), 0, 225, 567, 1,
                "Hungary", "link", "image", "user1 name");
        User user2 = new User(Set.of(collectiveItem), 35, 366, 150, 2,
                "Ukraine", "link", "image", "user2 name");
        ResponseWrapper wrapper = new ResponseWrapper(Set.of(user1, user2), false);

        when(userHttpClient.get(anyString())).thenReturn(wrapper);
        userService.displayFilteredUsers(Set.of("Moldova"), Set.of("java", "docker"), 223, 1);
        Mockito.verify(userHttpClient).get(anyString());
    }

    @AfterEach
    void tearDown() {
    }
}