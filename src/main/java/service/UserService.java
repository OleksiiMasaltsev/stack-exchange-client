package service;

import java.util.List;

public interface UserService {
    void displayFilteredUsers(List<String> countries, int reputationMin, int answerCountMin);
}
