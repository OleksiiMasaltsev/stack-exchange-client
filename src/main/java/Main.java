import service.impl.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> countries = List.of("Moldova", "Romania");
        List<String> tags = List.of("java", ".net", "docker", "C#");
        new UserServiceImpl().displayFilteredUsers(countries, 233, 1, tags);
    }
}
