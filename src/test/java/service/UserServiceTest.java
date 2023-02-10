package service;

import client.UserHttpClient;
import model.Collective;
import model.CollectiveItem;
import model.ResponseWrapper;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceTest {
    private UserService userService;
    private UserHttpClient userHttpClient;

    @BeforeEach
    void setUp() {
        userHttpClient = Mockito.mock(UserHttpClient.class);
        userService = new UserService(userHttpClient);
    }

    @Test
    void displayFilteredUsers_twoValidUsers_printsOnlyOne() {
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

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        userService.displayFilteredUsers(Set.of("Moldova"), Set.of("java", "docker"), 223, 1);

        assertEquals(user1.toString(), baos.toString().strip());
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

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        userService.displayFilteredUsers(Set.of("Moldova"), Set.of("java", "docker"), 223, 1);

        assertEquals("", baos.toString().strip());
        Mockito.verify(userHttpClient).get(anyString());
    }
}
