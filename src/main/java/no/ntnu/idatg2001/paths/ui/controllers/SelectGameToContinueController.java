package no.ntnu.idatg2001.paths.ui.controllers;

import java.util.List;
import java.util.Optional;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.Game;
import no.ntnu.idatg2001.paths.model.Story;
import no.ntnu.idatg2001.paths.model.dao.GameDAO;
import no.ntnu.idatg2001.paths.model.dao.PlayerDAO;
import no.ntnu.idatg2001.paths.model.dao.StoryDAO;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.alerts.ConfirmationAlert;
import no.ntnu.idatg2001.paths.ui.dialogs.ChangePlayerDialog;
import no.ntnu.idatg2001.paths.ui.dialogs.ChangeStoryDialog;
import no.ntnu.idatg2001.paths.ui.dialogs.NewPlayerDialog;
import no.ntnu.idatg2001.paths.ui.handlers.CurrentGameHandler;
import no.ntnu.idatg2001.paths.ui.views.SelectGameToContinueView;

public class SelectGameToContinueController {
  private final SelectGameToContinueView view;
  private final Stage stage;

  public SelectGameToContinueController(Stage stage) {
    this.stage = stage;
    this.view = new SelectGameToContinueView(this, stage);
  }

  public SelectGameToContinueView getView() {
    return view;
  }

  public void configureGamesTableView(
      TableView<Game> ongoingGamesTableView,
      TableColumn<Game, String> ongoingGamesPlayerTableColumn,
      TableColumn<Game, String> ongoingGamesStoryTableColumn) {
    ongoingGamesTableView.setPrefWidth(250);
    ongoingGamesPlayerTableColumn.setCellValueFactory(
        cellData -> {
          Game game = cellData.getValue();
          String playerName;
          if (game.getPlayer() == null) {
            playerName = "No player";
          } else {
            playerName = game.getPlayer().getName();
          }
          return new ReadOnlyStringWrapper(playerName);
        });
    ongoingGamesPlayerTableColumn.setPrefWidth(ongoingGamesTableView.getPrefWidth() / 2);

    ongoingGamesStoryTableColumn.setCellValueFactory(
        cellData -> {
          Game game = cellData.getValue();
          String storyTitle;
          if (game.getStory() == null) {
            storyTitle = "No story";
          } else {
            storyTitle = game.getStory().getTitle();
          }
          return new ReadOnlyStringWrapper(storyTitle);
        });
    ongoingGamesStoryTableColumn.setPrefWidth(ongoingGamesTableView.getPrefWidth() / 2);
  }

  public void updateGameTable(TableView<Game> ongoingGamesTableView) {
    ongoingGamesTableView.getItems().clear();
    List<Game> gameList = GameDAO.getInstance().getAll();
    // turn gameList into observable list
    ObservableList<Game> observableGameList = FXCollections.observableArrayList(gameList);
    ongoingGamesTableView.setItems(observableGameList);
  }

  public void onContinueGame(TableView<Game> ongoingGamesTableView) {
    if (ongoingGamesTableView.getSelectionModel().getSelectedItem() != null) {
      Game game = ongoingGamesTableView.getSelectionModel().getSelectedItem();
      if (game.getPlayer() == null) {
        if (PlayerDAO.getInstance().getAll().isEmpty()) {
          ConfirmationAlert confirmationAlert =
              new ConfirmationAlert(
                  // TODO: MAKE THIS LANGUAGE INDEPENDENT
                  "No player",
                  "There are no players in the database. Do you want to create a new player?");
          Optional<ButtonType> result = confirmationAlert.showAndWait();
          if (result.isPresent() && result.get() == ButtonType.OK) {
            NewPlayerDialog newPlayerDialog = new NewPlayerDialog();
            Optional<Player> newPlayerResult = newPlayerDialog.showAndWait();
            if (newPlayerResult.isPresent()) {
              game.setPlayer(newPlayerResult.get());
              GameDAO.getInstance().update(game);
              updateGameTable(ongoingGamesTableView);
            }
          }
        } else {
          ChangePlayerDialog changePlayerDialog = new ChangePlayerDialog(game.getPlayer());
          Optional<Player> result = changePlayerDialog.showAndWait();
          if (result.isPresent()) {
            game.setPlayer(result.get());
            GameDAO.getInstance().update(game);
            updateGameTable(ongoingGamesTableView);
          }
        }
      } else if (game.getStory() == null) {
        if (StoryDAO.getInstance().getAll().isEmpty()) {
          ConfirmationAlert confirmationAlert =
              new ConfirmationAlert(
                  // TODO: MAKE THIS LANGUAGE INDEPENDENT
                  "No story",
                  "There are no stories in the database. Do you want to create a new story?");
          Optional<ButtonType> result = confirmationAlert.showAndWait();
          if (result.isPresent() && result.get() == ButtonType.OK) {
            new NewStoryController(stage);
          }
        } else {
          ChangeStoryDialog changeStoryDialog = new ChangeStoryDialog(game.getStory());
          Optional<Story> result = changeStoryDialog.showAndWait();
          if (result.isPresent()) {
            game.setStory(result.get());
            GameDAO.getInstance().update(game);
            updateGameTable(ongoingGamesTableView);
          }
        }
      } else {
        CurrentGameHandler.setCurrentGame(game);
        // TODO: CHANGE CONTROLLER
        // new StoryViewController();
      }
    }
  }

  public void onChangePlayer(TableView<Game> ongoingGamesTableView) {
    if (ongoingGamesTableView.getSelectionModel().getSelectedItem() != null) {
      Player player = ongoingGamesTableView.getSelectionModel().getSelectedItem().getPlayer();
      ChangePlayerDialog changePlayerDialog = new ChangePlayerDialog(player);
      Optional<Player> result = changePlayerDialog.showAndWait();
      if (result.isPresent()) {
        ongoingGamesTableView.getSelectionModel().getSelectedItem().setPlayer(result.get());
        GameDAO.getInstance().update(ongoingGamesTableView.getSelectionModel().getSelectedItem());
        updateGameTable(ongoingGamesTableView);
      }
    }
  }

  public void onDeleteGame(TableView<Game> ongoingGamesTableView) {
    if (ongoingGamesTableView.getSelectionModel().getSelectedItem() != null) {
      Game game = ongoingGamesTableView.getSelectionModel().getSelectedItem();
      GameDAO.getInstance().remove(game);
      updateGameTable(ongoingGamesTableView);
    }
  }
}
