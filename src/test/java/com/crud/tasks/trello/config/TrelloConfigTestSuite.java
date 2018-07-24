package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TrelloConfigTestSuite {

    @Mock
    TrelloConfig trelloConfig;

    @Test
    public void testGetTrelloApiEndpoint() {
        //given
        String trelloApiEndpoint = "https://trello.com/1";
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn(trelloApiEndpoint);
        //when
        String excpectApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        //then
        Assert.assertEquals(trelloApiEndpoint, excpectApiEndpoint);
    }
    @Test
    public void testGetTrelloAppKey() {
        //given
        String trelloAppKey = "8ds6a513ds16as3d6";
        when(trelloConfig.getTrelloAppKey()).thenReturn(trelloAppKey);
        //when
        String excpectTrelloAppKey = trelloConfig.getTrelloAppKey();
        //then
        Assert.assertEquals(trelloAppKey, excpectTrelloAppKey);
    }

    @Test
    public void testGetTrelloAppToken() {
        //given
        String trelloAppToken = "cf545632dsd665d2s60fef4e987e67";
        when(trelloConfig.getTrelloToken()).thenReturn(trelloAppToken);
        //when
        String excpectTrelloAppToken = trelloConfig.getTrelloToken();
        //then
        Assert.assertEquals(trelloAppToken, excpectTrelloAppToken);
    }

    @Test
    public void testGetTrelloUsername() {
        //given
        String trelloUsername = "wszuwar@gmail.com";
        when(trelloConfig.getTrelloUsername()).thenReturn(trelloUsername);
        //when
        String excpectTrelloUsername = trelloConfig.getTrelloUsername();
        //then
        Assert.assertEquals(trelloUsername, excpectTrelloUsername);
    }
}
