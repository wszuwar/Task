package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloListDto {

    @JsonIgnoreProperties("id")
    private String id;

    @JsonIgnoreProperties("name")
    private String name;

    @JsonIgnoreProperties("closed")
    private boolean isClosed;
}
