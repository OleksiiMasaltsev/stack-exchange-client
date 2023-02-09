import service.UserService;
import java.util.List;

public class Main {

    private static final String MOLDOVA = "Moldova";
    private static final String ROMANIA = "Romania";
    private static final String UK = "UK";
    private static final String USA = "USA";
    private static final String JAVA = "java";
    private static final String DOT_NET = ".net";
    private static final String DOCKER = "docker";
    private static final String C_SHARP = "C#";
    private static final String GOOGLE = "google";
    private static final int REPUTATION_MIN = 233;
    private static final int ANSWER_COUNT_MIN = 1;

    public static void main(String[] args) {
        List<String> countries = List.of(MOLDOVA, ROMANIA, UK, USA);
        List<String> tags = List.of(JAVA, DOT_NET, DOCKER, C_SHARP, GOOGLE);
        new UserService().displayFilteredUsers(countries, tags , REPUTATION_MIN, ANSWER_COUNT_MIN);
    }
}
