package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CollectiveItem(@JsonProperty("collective") Collective collective) {}
