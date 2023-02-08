package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CollectiveExternal(@JsonProperty("collective") Collective collective) {}
