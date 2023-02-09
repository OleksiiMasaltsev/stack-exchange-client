package service;

import model.ResponseWrapper;
import model.User;
import client.UserHttpClient;
import java.util.List;
import java.util.Objects;

public class UserService {
    private static final String FILTER = "!P)usXvvTVLsqpq0WqvGhjAVUE0ev8qBGZ_)RDzS)62n";
    private static final String USERS_LINK = "https://api.stackexchange.com/2.3/users?" +
            "pagesize=100&order=desc&sort=reputation&site=stackoverflow&" +
            "filter=" + FILTER;
    private final UserHttpClient httpClient;

    public UserService() {
        httpClient  = new UserHttpClient();
    }

    public void displayFilteredUsers(List<String> countries, List<String> inputTags,
                                     int reputationMin, int answerCountMin) {
        ResponseWrapper responseWrapper;
        int page = 0;
        do {
            page++;
            responseWrapper = httpClient.get(USERS_LINK + "&min=" + reputationMin + "&page=" + page);
            List<User> users = responseWrapper.users();
            if (users == null) {
                break;
            }

            List<User> preFilteredUsers = users.stream()
                    .filter(user -> Objects.nonNull(user.location())
                            && Objects.nonNull(user.collectiveItems()))
                    .filter(user -> countries.stream()
                            .anyMatch(country -> user.location().contains(country)))
                    .filter(user -> user.answerCount() >= answerCountMin)
                    .toList();

            if (preFilteredUsers.size() > 0) {
                preFilteredUsers.stream()
                        .filter(user -> inputTags.stream()
                                .anyMatch(inputTag -> extractTags(user).stream()
                                        .anyMatch(tag -> tag.contains(inputTag))))
                        .forEach(System.out::println);
            }

        } while (responseWrapper.hasMore());
    }

    private List<String> extractTags(User user) {
        return user.collectiveItems().stream()
                .filter(Objects::nonNull)
                .flatMap(collectiveItem -> collectiveItem.collective().tags().stream())
                .toList();
    }
}
