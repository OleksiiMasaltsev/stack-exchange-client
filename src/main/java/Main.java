import service.UserService;
import service.impl.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> countries = List.of("Moldova", "Romania");
        UserService userService = new UserServiceImpl();
        userService.displayFilteredUsers(countries, 223, 1);
    }
}
