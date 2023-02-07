import model.Item;
import model.Root;
import util.ApiHttpClient;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ApiHttpClient httpClient = new ApiHttpClient();
        Root root;
        while (true) {
            root = httpClient.get("", Root.class);
            List<Item> items = root.getItems();
            List<Item> filteredItems = items.stream()
                    .filter(item -> item.getLocation().equals("Romania") || item.getLocation().equals("Moldova"))
                    .filter(item -> item.getReputation() > 222)
                    .filter(item -> item.getAnswerCount() > 0)
                    .toList();
            if (filteredItems.size() == 0) {
                continue;
            }
            if (!root.isHasMore()) {
                break;
            }


        }
    }
}
