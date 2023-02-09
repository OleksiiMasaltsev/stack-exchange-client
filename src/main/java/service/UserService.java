package service;

import model.CollectiveItem;
import model.Wrapper;
import model.User;
import client.RootHttpClient;
import java.util.Collections;
import java.util.List;

public class UserService {
    private static final String FILTER = "!P)usXvvTVLsqpq0WqvGhjAVUE0ev8qBGZ_)RDzS)62n";
    private static final String USERS_LINK = "https://api.stackexchange.com/2.3/users?" +
            "pagesize=100&order=desc&sort=reputation&site=stackoverflow&" +
            "filter=" + FILTER;
    private final RootHttpClient httpClient;

    public UserService() {
        httpClient  = new RootHttpClient();
    }

    public void displayFilteredUsers(List<String> countries, List<String> inputTags,
                                     int reputationMin, int answerCountMin) {
        Wrapper wrapper;
        int page = 0;
        do {
            page++;
            wrapper = httpClient.get(USERS_LINK + "&min=" + reputationMin + "&page=" + page);
            List<User> users = wrapper.users();
            if (users == null) {
                continue;
            }
            List<User> userList = users.stream()
                    .filter(user -> user.location() != null
                            && countries.contains(user.location())
                            && user.answerCount() >= answerCountMin)
                    .toList();

            if (userList.size() > 0) {
                for (User user : userList) {
                    List<String> checkedTags = checkTags(inputTags, user);
                    if (checkedTags.size() > 0) {
                        System.out.println(user);
                    }
                }
            }

        } while (wrapper.hasMore());
    }

    private List<String> checkTags(List<String> inputTags, User user) {
        return extractTags(user).stream()
                .filter(inputTags::contains)
                .toList();
    }

    private List<String> extractTags(User user) {
        List<CollectiveItem> externals = user.collectiveItems();
        if (externals == null) {
            return Collections.emptyList();
        } else {
            return externals.stream()
                    .flatMap(external -> external.collective().tags().stream())
                    .toList();
        }
    }
}
