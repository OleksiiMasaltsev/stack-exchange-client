package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record User (
        @JsonProperty("collectives")
        List<CollectiveItem> collectiveItems,
        @JsonProperty("answer_count")
        int answerCount,
        @JsonProperty("question_count")
        int questionCount,
        @JsonProperty("reputation")
        int reputation,
        @JsonProperty("user_id")
        int userId,
        @JsonProperty("location")
        String location,
        @JsonProperty("link")
        String link,
        @JsonProperty("profile_image")
        String profileImage,
        @JsonProperty("display_name")
        String displayName) {
        @Override
        public String toString() {
                List<String> tags = collectiveItems.stream()
                        .flatMap(collectiveItem -> collectiveItem.collective().tags().stream())
                        .toList();
                return displayName + " " + location + " " + answerCount + " " + questionCount
                        + " " + String.join(",", tags) + " " + link + " " + profileImage;
        }
}
