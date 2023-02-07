package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

public class Item {
    private List<Collective> collectives;
    @JsonProperty("answer_count")
    private int answerCount;
    @JsonProperty("question_count")
    private int questionCount;
    @JsonProperty("reputation")
    private int reputation;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("location")
    private String location;
    @JsonProperty("link")
    private String link;
    @JsonProperty("profile_image")
    private String profileImage;
    @JsonProperty("display_name")
    private String displayName;
}
