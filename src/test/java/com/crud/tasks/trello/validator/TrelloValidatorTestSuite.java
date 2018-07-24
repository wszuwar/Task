package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTestSuite {
    @Autowired
    TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards(){

        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "list1", false));

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", trelloLists));
        trelloBoards.add(new TrelloBoard("2", "test2", trelloLists));

        //When
        List<TrelloBoard> excpectedTrelloBoardList = trelloValidator.validateBoards(trelloBoards);

        //Then
        assertNotNull(excpectedTrelloBoardList);
        assertEquals(1, excpectedTrelloBoardList.size());
        excpectedTrelloBoardList.forEach(trelloBoard -> {
            assertEquals("2", trelloBoard.getId());
            assertEquals("test2", trelloBoard.getName());
        });
    }
}
