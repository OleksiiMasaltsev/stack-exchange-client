package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Collective(@JsonProperty("tags") List<String> tags) {
}
