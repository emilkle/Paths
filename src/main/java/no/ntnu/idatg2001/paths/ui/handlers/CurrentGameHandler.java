package no.ntnu.idatg2001.paths.ui.handlers;

import no.ntnu.idatg2001.paths.model.Game;

public class CurrentGameHandler {

  private CurrentGameHandler() {}
  private static Game currentGame;

  public static Game getCurrentGame() {
    return currentGame;
  }

  public static void setCurrentGame(Game currentGame) {
    CurrentGameHandler.currentGame = currentGame;
  }
}
