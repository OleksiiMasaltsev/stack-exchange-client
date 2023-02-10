package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public record User (
        @JsonProperty("collectives") Set<CollectiveItem> collectiveItems,
        @JsonProperty("answer_count") int answerCount,
        @JsonProperty("question_count") int questionCount,
        @JsonProperty("reputation") int reputation,
        @JsonProperty("user_id") int userId,
        @JsonProperty("location") String location,
        @JsonProperty("link") String link,
        @JsonProperty("profile_image") String profileImage,
        @JsonProperty("display_name") String displayName) {
        @Override
        public String toString() {
                Set<String> tags;
                if (collectiveItems == null) {
                        tags = Collections.emptySet();
                } else {
                        tags = collectiveItems.stream()
                                .filter(Objects::nonNull)
                                .flatMap(collectiveItem -> collectiveItem.collective().tags().stream())
                                .collect(Collectors.toSet());
                }
                return String.format("%s %s %d %d %s %s %s", displayName, location, answerCount,
                        questionCount, String.join(",", tags), link, profileImage);
        }
}
