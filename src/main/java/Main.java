import client.UserHttpClient;
import service.UserService;
import java.util.Set;

public class Main {
    private static final String MOLDOVA = "Moldova";
    private static final String ROMANIA = "Romania";
    private static final String JAVA = "java";
    private static final String DOT_NET = ".net";
    private static final String DOCKER = "docker";
    private static final String C_SHARP = "C#";
    private static final int MIN_REPUTATION = 233;
    private static final int MIN_ANSWER_COUNT = 1;

    public static void main(String[] args) {
        Set<String> countries = Set.of(MOLDOVA, ROMANIA);
        Set<String> tags = Set.of(JAVA, DOT_NET, DOCKER, C_SHARP);
        new UserService(new UserHttpClient()).displayFilteredUsers(
                countries, tags , MIN_REPUTATION, MIN_ANSWER_COUNT);
    }
}
