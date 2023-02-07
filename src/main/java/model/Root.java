package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class Root{
    private List<Item> items;
    @JsonProperty("has_more")
    private boolean hasMore;
}
