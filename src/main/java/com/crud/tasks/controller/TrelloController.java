package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

//    @Autowired
//    private TrelloService trelloService;
    @Autowired
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/boards")
    public List<TrelloBoardDto> getTrelloBoards() {

        return trelloFacade.fetchTrelloBoards();

//        trelloBoards.stream()
//                .filter(b-> b.getName()!=null)
//                .filter(b-> b.getName().contains("Kodilla"))
//                .filter(b->b.getId()!=null)
//                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName()));

    }

    @RequestMapping(method = RequestMethod.POST, value = "/cards")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloFacade.createCard(trelloCardDto);
    }
//    @RequestMapping(method = RequestMethod.POST, value = "/createTrelloCard")
//    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
//        return trelloService.createTrelloCard(trelloCardDto);
//    }

}
