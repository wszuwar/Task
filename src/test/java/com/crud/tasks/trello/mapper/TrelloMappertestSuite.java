package com.crud.tasks.trello.mapper;


import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMappertestSuite {

    @Autowired
     TrelloMapper trelloMapper;

    @Test
    public void testMapToBoardDto() {

        //Given
        TrelloList firstTrelloList = new TrelloList("7", "first", true);
        TrelloList secondTrelloList = new TrelloList("10", "second", false);
        TrelloList thirdTrelloList = new TrelloList("8", "third", true);
        List<TrelloList> trelloLists1 = Arrays.asList(firstTrelloList, secondTrelloList);
        List<TrelloList> trelloLists2 = Arrays.asList(thirdTrelloList);
        TrelloBoard trelloBoard1 = new TrelloBoard("2", "firstBoard", trelloLists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("3", "secondTrelloBoard", trelloLists2);
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard1, trelloBoard2);

        //When
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals("7", trelloBoardDto.get(0).getLists().get(0).getId());
        assertEquals("second", trelloBoardDto.get(0).getLists().get(1).getName());
        assertEquals(true, trelloBoardDto.get(1).getLists().get(0).isClosed());
        assertEquals(2, trelloBoardDto.size());
        assertEquals(3, trelloBoardDto.stream().map(s -> s.getLists()).flatMap(s -> s.stream()).count());
        assertEquals("firstBoard", trelloBoardDto.get(0).getName());
        assertEquals("3", trelloBoardDto.get(1).getId());
    }

    @Test
    public void testMapToBoard() {

        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("7", "first", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("10", "second", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("8", "third", true);
        List<TrelloListDto> dtoList1 = Arrays.asList(trelloListDto1);
        List<TrelloListDto> dtoList2 = Arrays.asList(trelloListDto2, trelloListDto3);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("2", "firstBoard", dtoList1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("3", "secondTrelloBoard", dtoList2);
        List<TrelloBoardDto> trelloBoardDtoList = Arrays.asList(trelloBoardDto1, trelloBoardDto2);

        //When
        List<TrelloBoard> trelloBoardsLists = trelloMapper.mapToBoard(trelloBoardDtoList);

        //Then
        assertEquals("7", trelloBoardsLists.get(0).getLists().get(0).getId());
        assertEquals("second", trelloBoardsLists.get(1).getLists().get(0).getName());
        assertEquals(2, trelloBoardsLists.stream().map(s -> s.getLists()).filter(s -> s.size() > 1).flatMap(s -> s.stream()).count());
        assertEquals(3, trelloBoardsLists.stream().map(s -> s.getLists()).flatMap(s -> s.stream()).count());
        assertEquals(2, trelloBoardsLists.stream().count());
    }

    @Test
    public void testMapToList() {
        //given
        TrelloListDto trelloListDto1 = new TrelloListDto("2", "first", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("4", "second", false);
        List<TrelloListDto> trelloListsDto = Arrays.asList(trelloListDto1, trelloListDto2);

        //when
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //then
        assertNotEquals(trelloListsDto, trelloLists);
        assertEquals(2, trelloLists.size());
        assertEquals("2", trelloLists.get(0).getId());
        assertEquals("second", trelloLists.get(1).getName());
        assertFalse(trelloLists.get(1).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //given
        TrelloList trelloList1 = new TrelloList("2", "first", true);
        TrelloList trelloList2 = new TrelloList("4", "second", false);
        List<TrelloList> trelloLists = Arrays.asList(trelloList1, trelloList2);

        //when
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);

        //then
        assertNotEquals(trelloLists, trelloListsDto);
        assertEquals(2, trelloListsDto.size());
        assertEquals("2", trelloListsDto.get(0).getId());
        assertEquals("second", trelloListsDto.get(1).getName());
        assertFalse(trelloListsDto.get(1).isClosed());
    }

    @Test
    public void testMapToCard() {
        //given
        TrelloCardDto trelloCardDto1 = new TrelloCardDto("Card", "description", "1", "2");

        //when
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto1);

        //then
        assertEquals("Card", trelloCard.getName());
        assertEquals("description", trelloCard.getDescription());
        assertEquals("1", trelloCard.getPos());
        assertEquals("2", trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //given
        TrelloCard trelloCard1 = new TrelloCard("Card", "description", "1", "2");

        //when
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard1);

        //then
        assertEquals("Card", trelloCardDto.getName());
        assertEquals("description", trelloCardDto.getDescription());
        assertEquals("1", trelloCardDto.getPos());
        assertEquals("2", trelloCardDto.getListId());
    }

}
