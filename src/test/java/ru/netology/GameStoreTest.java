package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GameStoreTest {

    GameStore store = new GameStore();

    @Test
    public void shouldAddGame() {

        Game game = store.publishGame("Игра1", "Аркады");

        assertTrue(store.containsGame(game));
    }

    // containsGame показывает, что игра есть, если одна игра в каталоге
    @Test
    public void shouldFindGameIfOnlyOneGameInCatalog() {

        Game game1 =  store.publishGame("Игра1", "Аркады");

        boolean expected = true;
        boolean actual = store.containsGame(game1);

        Assertions.assertEquals(expected, actual);
    }

    // containsGame показывает, что игра есть, если в каталоге две игры
    @Test
    public void shouldFindGameIfTwoGamesInCatalog() {

        Game game1 = store.publishGame("Игра1", "Аркады");
        Game game2 =store.publishGame("Игра2", "РПГ");

        boolean expected = true;
        boolean actual = store.containsGame(game1);

        Assertions.assertEquals(expected, actual);
    }

    // containsGame показывает, что игры нет, если каталог пуст
    @Test
    public void shouldNotFindGameIfCatalogIsEmpty() {

        Game game1 = new Game("Игра1", "Аркады", store);

        boolean expected = false;
        boolean actual = store.containsGame(game1);

        Assertions.assertEquals(expected, actual);
    }

    // getMostPlayer показывает игрока, который больше времени играл в игры каталога, если играл только один игрок
    @Test
    public void shouldFindPlayerWhoPlayedTheMostIfPlayerAlone() {

        store.addPlayTime("player1", 2);

        String expected = "player1";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    // getMostPlayer показывает игрока, который больше времени играл в игры каталога среди нескольких игроков
    @Test
    public void shouldFindPlayerWhoPlayedTheMostIfSeveralPlayers() {

        store.addPlayTime("player1", 2);
        store.addPlayTime("player2", 10);
        store.addPlayTime("player3", 7);

        String expected = "player2";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    // getMostPlayer показывает null, если игроков нет
    @Test
    public void shouldNotFindPlayerIfNobodyPlayed() {

        String expected = null;
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }
    // getMostPlayer показывает первого среди равных, если хотя бы 2 играли одинаковое время
    @Test
    public void shouldFindFirstPlayerWhoPlayedTheMostIfThereIsAPlayerWithEqualsTime() {

        store.addPlayTime("player1", 10);
        store.addPlayTime("player2", 2);
        store.addPlayTime("player3", 10);

        String expected = "player1";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    // getSumPlayedTime показывает общее количество времени всех игроков, если играли несколько игроков по разу
    @Test
    public void shouldSumTimeOfAllPlayersIfSeveralPlayers() {

        store.addPlayTime("player1", 2);
        store.addPlayTime("player2", 10);
        store.addPlayTime("player3", 7);

        int expected = 19;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    // getSumPlayedTime показывает общее количество времени всех игроков, если играли несколько игроков по два раза
    @Test
    public void shouldSumTimeOfAllPlayersIfSeveralPlayersPlayedTwice() {

        store.addPlayTime("player1", 2);
        store.addPlayTime("player1", 8);
        store.addPlayTime("player2", 10);
        store.addPlayTime("player2", 10);
        store.addPlayTime("player3", 7);
        store.addPlayTime("player3", 3);

        int expected = 40;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    // getSumPlayedTime показывает общее количество времени всех игроков, если играл только один и один раз
    @Test
    public void shouldSumTimeOfAllPlayersIfOnePlayerPlayedOnce() {

        store.addPlayTime("player1", 2);

        int expected = 2;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    // getSumPlayedTime показывает общее количество времени всех игроков, если играл только один, но 2 раза
    @Test
    public void shouldSumTimeOfAllPlayersIfOnePlayerPlayedTwice() {

        store.addPlayTime("player1", 2);
        store.addPlayTime("player1", 10);

        int expected = 12;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    // getSumPlayedTime показывает общее количество времени всех игроков, если никто не играл
    @Test
    public void shouldSumTimeOfAllPlayersIfNobodyPlayed() {

        int expected = 0;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }
//
//    // addPlayTime показывает сколько времени сыграл один игрок за один раз
//    @Test
//    public void shouldShowPlayedTimeIfPlayedOnce() {
//
//        store.addPlayTime("player1", 2);
//
//        int expected = 2;
//        int actual = store.getSumPlayedTime();
//
//        Assertions.assertEquals(expected, actual);
//    }
//
//    // addPlayTime регистрирует время игрока, сыгравшего 2 раза
//    @Test
//    public void shouldShowPlayedTimeIfPlayedTwice() {
//
//        store.addPlayTime("player1", 2);
//        store.addPlayTime("player1", 8);
//
//        int expected = 10;
//        int actual = store.getSumPlayedTime();
//
//        Assertions.assertEquals(expected, actual);
//    }
//    // addPlayTime не регpистрирует никакого времени, если игрок не играл вообще.
//    @Test
//    public void shouldShowPlayedTimeIfNobodyPlayed() {
//
//        int expected = 0;
//        int actual = store.getSumPlayedTime();
//
//        Assertions.assertEquals(expected, actual);
//    }
}