package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Collective (@JsonProperty("collective") Tag collective) {}
