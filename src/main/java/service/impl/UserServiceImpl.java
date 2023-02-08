package service.impl;

import model.CollectiveExternal;
import model.Root;
import model.User;
import service.UserService;
import util.RootHttpClient;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final String FILTER = "!P)usXvvTVLsqpq0WqvGhjAVUE0ev8qBGZ_)RDzS)62n";
    private static final String USERS_LINK = "https://api.stackexchange.com/2.3/users?" +
            "order=desc&sort=reputation&site=stackoverflow&" +
            "filter=" + FILTER;
    private final RootHttpClient httpClient;

    public UserServiceImpl() {
        httpClient  = new RootHttpClient();
    }

    @Override
    public void displayFilteredUsers(List<String> countries, int reputationMin, int answerCountMin) {
        Root root;
        int page = 0;
        do {
            page++;
            root = httpClient.get(USERS_LINK.concat("&min=").concat(String.valueOf(reputationMin))
                    .concat("&page=") + page);
            List<User> userList = root.users().stream()
                    .filter(user -> user.location() != null
                            && countries.stream()
                            .anyMatch(country -> user.location().contains(country))
                            && user.answerCount() >= answerCountMin)
                    .toList();

            userList = getUserTags(userList);
            userList.forEach(user -> System.out.println(user.displayName() + " " + user.location()
            + " " + user.answerCount() + " " + user.questionCount()));

        } while (root.hasMore());
    }

    private List<User> getUserTags(List<User> userList) {
        List<User> usersWithProperTags = new ArrayList<>();
        for (User user : userList) {
            List<CollectiveExternal> externals = user.collectiveExternals();
            for (CollectiveExternal external : externals) {
                List<String> tags = external.collective().tags();
                for (String tag : tags) {
                    if (tag.equals("java") || tag.equals(".net")
                    || tag.equals("docker") || tag.equals("C#")) {
                        usersWithProperTags.add(user);
                    }
                }
            }
        }
        return usersWithProperTags;
    }
}
