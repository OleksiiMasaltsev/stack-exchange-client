package service.impl;

import model.CollectiveExternal;
import model.Root;
import model.User;
import service.UserService;
import util.RootHttpClient;
import java.util.Collections;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final String FILTER = "!P)usXvvTVLsqpq0WqvGhjAVUE0ev8qBGZ_)RDzS)62n";
    private static final String USERS_LINK = "https://api.stackexchange.com/2.3/users?" +
            "pagesize=100&order=desc&sort=reputation&site=stackoverflow&" +
            "filter=" + FILTER;
    private final RootHttpClient httpClient;

    public UserServiceImpl() {
        httpClient  = new RootHttpClient();
    }

    @Override
    public void displayFilteredUsers(List<String> countries, int reputationMin,
                                     int answerCountMin, List<String> inputTags) {
        Root root;
        // TODO set to 0 before release
        int page = 0;
        do {
            page++;
            root = httpClient.get(USERS_LINK + "&min=" + reputationMin + "&page=" + page);
            List<User> users = root.users();
            if (users == null) {
                continue;
            }
            List<User> userList = users.stream()
                    .filter(user -> user.location() != null
                            && countries.stream()
                                .anyMatch(country -> user.location().contains(country))
                            && user.answerCount() >= answerCountMin)
                    .toList();

            if (userList.size() > 0) {
                for (User user : userList) {
                    List<String> checkedTags = checkTags(inputTags, user);
                    if (checkedTags.size() > 0) {
                        printResults(user, checkedTags);
                    }
                }
            }

        } while (root.hasMore());
    }

    private List<String> checkTags(List<String> inputTags, User user) {
        return extractTags(user).stream()
                .filter(tag -> inputTags.stream()
                        .anyMatch(tag::contains))
                .toList();
    }

    private List<String> extractTags(User user) {
        List<CollectiveExternal> externals = user.collectiveExternals();
        if (externals == null) {
            return Collections.emptyList();
        } else {
            return externals.stream()
                    .flatMap(external -> external.collective().tags().stream())
                    .toList();
        }
    }

    private void printResults(User user, List<String> tags) {
        System.out.println(
                user.displayName() + " "
                        + user.location() + " "
                        + user.answerCount() + " "
                        + user.questionCount() + " "
                        + String.join(",", tags) + " "
                        + user.link() + " "
                        + user.profileImage()
        );
    }
}
