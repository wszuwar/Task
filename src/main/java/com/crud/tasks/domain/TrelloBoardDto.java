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
    @JsonIgnoreProperties("id")
    private String id;

    @JsonIgnoreProperties("name")
    private String name;



    @JsonIgnoreProperties("lists")
    private List<TrelloListDto> lists;
}
