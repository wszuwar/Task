package com.crud.tasks.trello.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.service.SimplyEmailService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.TrelloConfig;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServicetestSuite {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Mock
    TrelloConfig trelloConfig;

    @Mock
    AdminConfig adminConfig;

    @Mock
    SimplyEmailService simplyEmailService;

    @Test
    public void testCreateTrelloCard(){
        //Given


        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id");

        //when
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1",
                "Test task",
                "http://test.com",
                new Badges(1,
                        new AttachmentsByType(
                                new Trello(11, 22)
                        ))
        );
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        CreatedTrelloCardDto newCard = trelloService.createTrelloCard(trelloCardDto);

        //then


        assertNotNull(newCard);
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals(1, newCard.getBadges().getVotes());
        assertEquals(11, newCard.getBadges().getAttachments().getTrello().getBoard());
        assertEquals(22, newCard.getBadges().getAttachments().getTrello().getCard());
    }

    @Test
    public void testFetchTrelloBoards() {
        //given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("1", "test", trelloListsDto));

        //when

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardsDto);
        List<TrelloBoardDto> fetchedTrelloBoards = trelloService.fetchTrelloBoards();
        //then

       assertNotNull(fetchedTrelloBoards);
       assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("1", fetchedTrelloBoards.get(0).getId());
        assertEquals("test", fetchedTrelloBoards.get(0).getName());
        assertEquals(trelloListsDto, fetchedTrelloBoards.get(0).getLists());
    }
}
