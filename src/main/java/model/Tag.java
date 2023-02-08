package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record Tag(@JsonProperty("tags") List<String> tags) {
}
