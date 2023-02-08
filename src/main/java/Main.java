import model.User;
import model.Root;
import util.RootHttpClient;
import java.util.List;

public class Main {
    private static final String USERS_LINK = "https://api.stackexchange.com/2.3/users?"
            + "order=desc&sort=reputation&site=stackoverflow&"
            + "filter=!*3.pxT1Q*mNt.Bqm5NI6AcBXr0VyFDfE(qdzpLw_F";
    private static final String MOLDOVA = "Moldova";
    private static final String ROMANIA = "Romania";

    public static void main(String[] args) {
        RootHttpClient httpClient = new RootHttpClient();
        Root root;
        do {
            root = httpClient.get(USERS_LINK);
            List<User> users = root.users();
            List<User> filteredUsers = users.stream()
                    .filter(user -> user.location().contains(ROMANIA)
                            || user.location().contains(MOLDOVA))
                    .filter(user -> user.reputation() > 222)
                    .filter(user -> user.answerCount() > 0)
                    .toList();

            filteredUsers.forEach(user -> System.out.println(user.displayName()));

        } while (root.hasMore());
    }
}
