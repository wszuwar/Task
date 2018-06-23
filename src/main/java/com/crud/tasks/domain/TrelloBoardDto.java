package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBoardDto {

    @JsonIgnoreProperties("name")
    private String name;

    @JsonIgnoreProperties("id")
    private String id;

    @JsonIgnoreProperties("lists")
    private List<TrelloListDto> lists;
}
