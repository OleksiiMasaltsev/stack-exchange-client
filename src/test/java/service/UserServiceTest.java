package service;

import client.UserHttpClient;
import model.Collective;
import model.CollectiveItem;
import model.ResponseWrapper;
import model.User;
import org.junit.jupiter.api.AfterEach;
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
    private PrintStream stdOut;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        userHttpClient = Mockito.mock(UserHttpClient.class);
        userService = new UserService(userHttpClient);

        stdOut = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void displayFilteredUsers_twoValidUsers_printsTwo() {
        Set<String> tags1 = Set.of("azure-digital-twins",
                "java-secrets",
                "azure-functions-docker",
                "azure-ml-component");
        Set<String> tags2 = Set.of("azure-digital-twins",
                "java-storage",
                "google-cloud-spanner");

        Collective collective1 = new Collective(tags1);
        Collective collective2 = new Collective(tags2);
        CollectiveItem collectiveItem1 = new CollectiveItem(collective1);
        CollectiveItem collectiveItem2 = new CollectiveItem(collective2);

        User user1 = new User(Set.of(collectiveItem1), 45, 225, 567, 1,
                "Moldova", "link1", "image1", "user1 name");
        User user2 = new User(Set.of(collectiveItem2), 35, 366, 781, 2,
                "Romania", "link2", "image2", "user2 name");

        ResponseWrapper wrapper = new ResponseWrapper(Set.of(user1, user2), false);

        when(userHttpClient.get(anyString())).thenReturn(wrapper);
        userService.displayFilteredUsers(Set.of("Moldova", "Romania"), Set.of("java", "docker"), 223, 1);
        assertEquals(user2 + System.lineSeparator() + user1, outputStream.toString().strip());
        Mockito.verify(userHttpClient).get(anyString());
    }

    @Test
    void displayFilteredUsers_twoUsersOneValid_printsOne() {
        Set<String> tags1 = Set.of("azure-digital-twins",
                "java-secrets",
                "azure-functions-docker",
                "azure-ml-component");
        Set<String> tags2 = Set.of("azure-digital-twins",
                "java-storage",
                "google-cloud-spanner");

        Collective collective1 = new Collective(tags1);
        Collective collective2 = new Collective(tags2);
        CollectiveItem collectiveItem1 = new CollectiveItem(collective1);
        CollectiveItem collectiveItem2 = new CollectiveItem(collective2);

        User user1 = new User(Set.of(collectiveItem1), 45, 225, 567, 1,
                "Hungary", "link1", "image1", "user1 name");
        User user2 = new User(Set.of(collectiveItem2), 35, 366, 781, 2,
                "Romania", "link2", "image2", "user2 name");

        ResponseWrapper wrapper = new ResponseWrapper(Set.of(user1, user2), false);

        when(userHttpClient.get(anyString())).thenReturn(wrapper);
        userService.displayFilteredUsers(Set.of("Moldova", "Romania"), Set.of("java", "docker"), 223, 1);
        assertEquals(user2.toString(), outputStream.toString().strip());
        Mockito.verify(userHttpClient).get(anyString());
    }

    @Test
    void displayFilteredUsers_twoInvalidUsers_printsNothing() {
        Set<String> tags1 = Set.of(
                "azure-functions-docker",
                "azure-ml-component");
        Set<String> tags2 = Set.of("azure-digital-twins");

        Collective collective1 = new Collective(tags1);
        Collective collective2 = new Collective(tags2);
        CollectiveItem collectiveItem1 = new CollectiveItem(collective1);
        CollectiveItem collectiveItem2 = new CollectiveItem(collective2);

        User user1 = new User(Set.of(collectiveItem1), 0, 225, 300, 1,
                "Moldova", "link1", "image1", "user1 name");
        User user2 = new User(Set.of(collectiveItem2), 35, 366, 781, 2,
                "Serbia", "link2", "image2", "user2 name");

        ResponseWrapper wrapper = new ResponseWrapper(Set.of(user1, user2), false);
        when(userHttpClient.get(anyString())).thenReturn(wrapper);
        userService.displayFilteredUsers(Set.of("Moldova", "Romania"), Set.of("java", "docker"), 223, 1);
        assertEquals("", outputStream.toString().strip());
        Mockito.verify(userHttpClient).get(anyString());
    }

    @Test
    void displayFilteredUsers_OneUserWithoutCollectives_printsNothing() {
        User user = new User(null, 0, 225, 300, 1,
                "Moldova", "link1", "image1", "user1 name");

        ResponseWrapper wrapper = new ResponseWrapper(Set.of(user), false);
        when(userHttpClient.get(anyString())).thenReturn(wrapper);
        userService.displayFilteredUsers(Set.of("Moldova", "Romania"), Set.of("java", "docker"), 223, 1);
        assertEquals("", outputStream.toString().strip());
        Mockito.verify(userHttpClient).get(anyString());
    }

    @AfterEach
    void tearDown() {
        System.setOut(stdOut);
    }
}
