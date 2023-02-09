package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Wrapper(@JsonProperty("items") List<User> users,
                      @JsonProperty("has_more") boolean hasMore) {}
