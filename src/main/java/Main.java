import model.Item;
import model.Root;
import util.ApiHttpClient;
import java.util.List;

public class Main {
//    private static final String USERS_LINK = "https://api.stackexchange.com/2.3/users?order=desc&sort=reputation&site=stackoverflow&filter=!*3.pxT1Q*mNt.Bqm5NI6AcBXr0VyFDfE(qdzpLw_Fhttps://api.stackexchange.com/2.3/users?order=desc&sort=reputation&site=stackoverflow&filter=!*3.pxT1Q*mNt.Bqm5NI6AcBXr0VyFDfE(qdzpLw_F";
    private static final String USERS_LINK = "https://api.stackexchange.com/2.3/users?order=desc&sort=reputation&site=stackoverflow";

    public static void main(String[] args) {
        ApiHttpClient httpClient = new ApiHttpClient();
        Root root;
        do {
            root = httpClient.get(USERS_LINK);
            List<Item> items = root.items();
            List<Item> filteredItems = items.stream()
                    .filter(item -> item.getLocation().equals("Romania") || item.getLocation().equals("Moldova"))
                    .filter(item -> item.getReputation() > 222)
                    .filter(item -> item.getAnswerCount() > 0)
                    .toList();

            filteredItems.forEach(item -> System.out.println(item.getDisplayName()));

        } while (root.hasMore());
    }
}
