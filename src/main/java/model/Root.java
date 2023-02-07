package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Root(List<Item> items, @JsonProperty("has_more") boolean hasMore) {
}
