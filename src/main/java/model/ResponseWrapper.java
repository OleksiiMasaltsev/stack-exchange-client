package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

public record ResponseWrapper(@JsonProperty("items") Set<User> users,
                              @JsonProperty("has_more") boolean hasMore) {}
