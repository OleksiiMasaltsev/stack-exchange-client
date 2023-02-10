package service;

import client.UserHttpClient;
import model.ResponseWrapper;
import model.User;
import java.util.Objects;
import java.util.Set;

public class UserService {
    private static final String FILTER = "!P)usXvvTVLsqpq0WqvGhjAVUE0ev8qBGZ_)RDzS)62n";
    private static final String USERS_LINK = "https://api.stackexchange.com/2.3/users?" +
            "pagesize=100&order=desc&sort=reputation&site=stackoverflow&" +
            "filter=" + FILTER;
    private final UserHttpClient userHttpClient;

    public UserService(UserHttpClient userHttpClient) {
        this.userHttpClient = userHttpClient;
    }

    public void displayFilteredUsers(Set<String> inputCountries, Set<String> inputTags,
                                     int minReputation, int minAnswerCount) {
        ResponseWrapper responseWrapper;
        int page = 0;
        do {
            page++;
            responseWrapper = userHttpClient.get(USERS_LINK + "&min=" + minReputation + "&page=" + page);
            Set<User> users = responseWrapper.users();
            if (users == null) {
                break;
            }

            users.stream()
                    .filter(user -> Objects.nonNull(user.location()))
                    .filter(user -> Objects.nonNull(user.collectiveItems()))
                    .filter(user -> user.answerCount() >= minAnswerCount)
                    .filter(user -> inputCountries.stream()
                            .anyMatch(inputCountry -> user.location().contains(inputCountry)))
                    .filter(user -> inputTags.stream()
                            .anyMatch(inputTag -> user.collectiveItems().stream()
                                    .filter(Objects::nonNull)
                                    .flatMap(collectiveItem -> collectiveItem.collective().tags().stream())
                                    .anyMatch(tag -> tag.contains(inputTag))))
                    .forEach(System.out::println);

        } while (responseWrapper.hasMore());
    }
}
