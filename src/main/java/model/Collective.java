package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

public record Collective(@JsonProperty("tags") Set<String> tags) {
}
