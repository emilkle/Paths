package no.ntnu.idatg2001.paths.ui.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatg2001.paths.model.units.DefaultAttributes;
import no.ntnu.idatg2001.paths.model.units.Player;
import no.ntnu.idatg2001.paths.ui.controllers.EditPlayerController;
import no.ntnu.idatg2001.paths.ui.controllers.EditStoryController;
import no.ntnu.idatg2001.paths.ui.handlers.LanguageHandler;
import no.ntnu.idatg2001.paths.ui.handlers.MusicHandler;
import no.ntnu.idatg2001.paths.ui.standardObjects.StandardMenuBar;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class that creates the view for editing a player. This class uses getters for accessing the
 * different fields, so that the controller can access them.
 *
 * @version 1.0.0
 */
public class EditPlayerView implements View {
  private final Player player;
  private final EditPlayerController controller;
  private final VBox centerVBox;
  private final HBox contentHBox;
  private final Text editPlayerText;
  private final HBox buttonsHBox;
  private final Stage primaryStage;
  private VBox leftVBox;
  private VBox rightVBox;
  private GridPane cheatsGridPane;
  private GridPane playerGridPane;
  private GridPane attributesGridPane;
  private Text playerText;
  private Text cheatsText;
  private Text nameText;
  private Text healthText;
  private Text manaText;
  private Text energyText;
  private Text goldText;
  private Text scoreText;
  private TextField nameField;
  private TextField healthField;
  private TextField manaField;
  private TextField energyField;
  private TextField goldField;
  private TextField scoreField;
  private Text attributesText;
  private Text strengthText;
  private Text perceptionText;
  private Text enduranceText;
  private Text charismaText;
  private Text intelligenceText;
  private Text agilityText;
  private Text luckText;
  private TextField strengthTextField;
  private TextField perceptionTextField;
  private TextField enduranceTextField;
  private TextField charismaTextField;
  private TextField intelligenceTextField;
  private TextField agilityTextField;
  private TextField luckTextField;
  private Button saveButton;
  private Button cancelButton;
  private ComboBox<DefaultAttributes> defaultAttributesComboBox;
  private Button showAttributesGridPaneButton;

  public EditPlayerView(EditPlayerController controller, Stage primaryStage, Player player) {
    this.controller = controller;
    this.primaryStage = primaryStage;
    this.player = player;
    primaryStage.setTitle("Edit Player");

    BorderPane root = new BorderPane();
    root.setTop(new StandardMenuBar(primaryStage));

    centerVBox = new VBox();
    centerVBox.setAlignment(Pos.TOP_CENTER);
    centerVBox.setPrefSize(100, 200);
    root.setCenter(centerVBox);

    editPlayerText = new Text();
    centerVBox.getChildren().add(editPlayerText);

    contentHBox = new HBox();
    contentHBox.setAlignment(Pos.CENTER);
    contentHBox.setPrefSize(600, 251);
    centerVBox.getChildren().add(contentHBox);

    leftVBox = createLeftVBox();
    contentHBox.getChildren().add(leftVBox);

    rightVBox = createRightVBox();
    contentHBox.getChildren().add(rightVBox);

    buttonsHBox = createButtonsHBox();
    buttonsHBox.setAlignment(Pos.CENTER);
    centerVBox.getChildren().add(buttonsHBox);

    // Observes when the language is changed, then calls updateLanguage()
    LanguageHandler.getObservableIntegerCounter().addListener((a, b, c) -> updateLanguage());
    updateLanguage();

    addParametersFromPlayerIntoTextFields();

    // CONTROLLER

    MusicHandler.playMusic("Old_Man.mp3");
    primaryStage.getScene().setRoot(root);
  }

  private HBox createButtonsHBox() {
    HBox buttonsHBox = new HBox();

    cancelButton = new Button();
    cancelButton.setOnAction(
        event -> {
          HomeView homeView = new HomeView();
          homeView.start(primaryStage);
        });

    saveButton = new Button();
    saveButton.setOnAction(
        event -> {
          controller.savePlayer();
          HomeView homeView = new HomeView();
          homeView.start(primaryStage);
        });

    buttonsHBox.getChildren().addAll(cancelButton, saveButton);

    return buttonsHBox;
  }

  private VBox createLeftVBox() {
    leftVBox = new VBox();
    leftVBox.setPrefSize(200, 200);

    playerText = new Text();
    leftVBox.getChildren().add(playerText);

    playerGridPane = createPlayerGridPane();
    leftVBox.getChildren().add(playerGridPane);

    cheatsText = new Text();
    leftVBox.getChildren().add(cheatsText);

    cheatsGridPane = createCheatsGridPane();
    leftVBox.getChildren().add(cheatsGridPane);

    return leftVBox;
  }

  private GridPane createPlayerGridPane() {
    GridPane playerGridPane = new GridPane();

    ColumnConstraints columnConstraints1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
    ColumnConstraints columnConstraints2 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
    playerGridPane.getColumnConstraints().addAll(columnConstraints1, columnConstraints2);

    for (int i = 0; i < 4; i++) {
      RowConstraints rowConstraints = new RowConstraints(30, 30, Double.MAX_VALUE);
      playerGridPane.getRowConstraints().add(rowConstraints);
    }

    nameText = new Text();
    playerGridPane.add(nameText, 0, 0);
    nameField = new TextField();
    playerGridPane.add(nameField, 1, 0);

    healthText = new Text();
    playerGridPane.add(healthText, 0, 1);
    healthField = new TextField();
    playerGridPane.add(healthField, 1, 1);

    manaText = new Text();
    playerGridPane.add(manaText, 0, 2);
    manaField = new TextField();
    playerGridPane.add(manaField, 1, 2);

    energyText = new Text();
    playerGridPane.add(energyText, 0, 3);
    energyField = new TextField();
    playerGridPane.add(energyField, 1, 3);

    return playerGridPane;
  }

  private GridPane createCheatsGridPane() {
    GridPane cheatsGridPane = new GridPane();

    ColumnConstraints col1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
    ColumnConstraints col2 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
    cheatsGridPane.getColumnConstraints().addAll(col1, col2);

    for (int i = 0; i < 2; i++) {
      RowConstraints row = new RowConstraints(30, 30, Double.MAX_VALUE);
      cheatsGridPane.getRowConstraints().add(row);
    }

    goldText = new Text("Gold");
    cheatsGridPane.add(goldText, 0, 0);
    goldField = new TextField();
    cheatsGridPane.add(goldField, 1, 0);

    scoreText = new Text("Score");
    cheatsGridPane.add(scoreText, 0, 1);
    scoreField = new TextField();
    cheatsGridPane.add(scoreField, 1, 1);

    return cheatsGridPane;
  }

  private VBox createRightVBox() {
    rightVBox = new VBox();
    rightVBox.setPrefSize(200, 200);

    attributesText = new Text("Attributes");
    rightVBox.getChildren().add(attributesText);

    attributesGridPane = createAttributesGridPane();

    defaultAttributesComboBox = new ComboBox<>();
    showAttributesGridPaneButton = new Button();

    rightVBox
        .getChildren()
        .addAll(showAttributesGridPaneButton, defaultAttributesComboBox, attributesGridPane);

    return rightVBox;
  }

  private GridPane createAttributesGridPane() {
    GridPane attributesGridPane = new GridPane();

    ColumnConstraints col1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
    ColumnConstraints col2 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
    attributesGridPane.getColumnConstraints().addAll(col1, col2);

    for (int i = 0; i < 7; i++) {
      RowConstraints row = new RowConstraints(30, 30, Double.MAX_VALUE);
      attributesGridPane.getRowConstraints().add(row);
    }

    strengthText = new Text();
    perceptionText = new Text();
    enduranceText = new Text();
    charismaText = new Text();
    intelligenceText = new Text();
    agilityText = new Text();
    luckText = new Text();

    strengthTextField = new TextField();
    perceptionTextField = new TextField();
    enduranceTextField = new TextField();
    charismaTextField = new TextField();
    intelligenceTextField = new TextField();
    agilityTextField = new TextField();
    luckTextField = new TextField();

    attributesGridPane.add(strengthText, 0, 0);
    attributesGridPane.add(strengthTextField, 1, 0);
    attributesGridPane.add(perceptionText, 0, 1);
    attributesGridPane.add(perceptionTextField, 1, 1);
    attributesGridPane.add(enduranceText, 0, 2);
    attributesGridPane.add(enduranceTextField, 1, 2);
    attributesGridPane.add(charismaText, 0, 3);
    attributesGridPane.add(charismaTextField, 1, 3);
    attributesGridPane.add(intelligenceText, 0, 4);
    attributesGridPane.add(intelligenceTextField, 1, 4);
    attributesGridPane.add(agilityText, 0, 5);
    attributesGridPane.add(agilityTextField, 1, 5);
    attributesGridPane.add(luckText, 0, 6);
    attributesGridPane.add(luckTextField, 1, 6);

    return attributesGridPane;
  }

  @Override
  public void updateLanguage() {
    ResourceBundle resources =
        ResourceBundle.getBundle(
            "editPlayer",
            Locale.forLanguageTag(LanguageHandler.getCurrentLanguage().getLocalName()));
    playerText.setText(resources.getString("playerText"));
    cheatsText.setText(resources.getString("cheatsText"));
    editPlayerText.setText(resources.getString("editPlayerText"));
    nameText.setText(resources.getString("nameText"));
    healthText.setText(resources.getString("healthText"));
    manaText.setText(resources.getString("manaText"));
    energyText.setText(resources.getString("energyText"));
    goldText.setText(resources.getString("goldText"));
    scoreText.setText(resources.getString("scoreText"));
    attributesText.setText(resources.getString("attributesText"));
    strengthText.setText(resources.getString("strengthText"));
    perceptionText.setText(resources.getString("perceptionText"));
    enduranceText.setText(resources.getString("enduranceText"));
    charismaText.setText(resources.getString("charismaText"));
    intelligenceText.setText(resources.getString("intelligenceText"));
    agilityText.setText(resources.getString("agilityText"));
    luckText.setText(resources.getString("luckText"));

    cancelButton.setText(resources.getString("cancelButton"));
    saveButton.setText(resources.getString("saveButton"));

    defaultAttributesComboBox.setPromptText(resources.getString("defaultAttributesComboBox"));
  }

  public void addParametersFromPlayerIntoTextFields() {
    nameField.setText(player.getName());

    healthField.setText(String.valueOf(player.getHealth()));
    manaField.setText(String.valueOf(player.getMana()));
    energyField.setText(String.valueOf(player.getEnergy()));
    goldField.setText(String.valueOf(player.getGold()));
    scoreField.setText(String.valueOf(player.getScore()));

    strengthTextField.setText(String.valueOf(player.getAttributes().getStrength()));
    perceptionTextField.setText(String.valueOf(player.getAttributes().getPerception()));
    enduranceTextField.setText(String.valueOf(player.getAttributes().getEndurance()));
    charismaTextField.setText(String.valueOf(player.getAttributes().getCharisma()));
    intelligenceTextField.setText(String.valueOf(player.getAttributes().getIntelligence()));
    agilityTextField.setText(String.valueOf(player.getAttributes().getAgility()));
    luckTextField.setText(String.valueOf(player.getAttributes().getLuck()));
  }

  public boolean assertStandardFieldsValid() {
    return assertTextFieldValid(nameField)
        && assertIntegerFieldValid(healthField)
        && assertIntegerFieldValid(manaField)
        && assertIntegerFieldValid(energyField)
        && assertIntegerFieldValid(goldField)
        && assertIntegerFieldValid(scoreField);
  }

  public boolean assertAttributesFieldsValid() {
    return assertIntegerFieldValid(strengthTextField)
        && assertIntegerFieldValid(perceptionTextField)
        && assertIntegerFieldValid(enduranceTextField)
        && assertIntegerFieldValid(charismaTextField)
        && assertIntegerFieldValid(intelligenceTextField)
        && assertIntegerFieldValid(agilityTextField)
        && assertIntegerFieldValid(luckTextField);
  }

  public boolean assertAllFieldsValid() {
    return assertStandardFieldsValid() && assertAttributesFieldsValid();
  }

  private boolean assertTextFieldValid(TextField textField) {
    return !textField.getText().isEmpty() && !textField.getText().isBlank();
  }

  private boolean assertIntegerFieldValid(TextField textField) {
    return textField.getText().matches("[0-9]+");
  }

  public TextField getNameField() {
    return nameField;
  }

  public TextField getHealthField() {
    return healthField;
  }

  public TextField getManaField() {
    return manaField;
  }

  public TextField getEnergyField() {
    return energyField;
  }

  public TextField getGoldField() {
    return goldField;
  }

  public TextField getScoreField() {
    return scoreField;
  }

  public TextField getStrengthTextField() {
    return strengthTextField;
  }

  public TextField getPerceptionTextField() {
    return perceptionTextField;
  }

  public TextField getEnduranceTextField() {
    return enduranceTextField;
  }

  public TextField getCharismaTextField() {
    return charismaTextField;
  }

  public TextField getIntelligenceTextField() {
    return intelligenceTextField;
  }

  public TextField getAgilityTextField() {
    return agilityTextField;
  }

  public TextField getLuckTextField() {
    return luckTextField;
  }

  public ComboBox<DefaultAttributes> getDefaultAttributesComboBox() {
    return defaultAttributesComboBox;
  }
}
