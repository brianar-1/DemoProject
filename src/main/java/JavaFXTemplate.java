import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.animation.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;
import java.util.stream.Collectors;


import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


//Briana Rendon

public class JavaFXTemplate extends Application {

    private Stage primaryStage;
    private Scene welcomeScene;
    private Scene gameScene;
    private List<Integer> selectedNumbers = new ArrayList<>();
    private Button[] numberButtons = new Button[80];


    private int totalWinnings = 0;
    private int totalDrawings = 0;
    private int currentDraw = 0;
    private Button continueButton;
    private HBox bottomBox;

    private Label winningsLabel;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Keno Game");

        //menu options
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");

        MenuItem rulesItem = new MenuItem("Rules");
        MenuItem oddsItem = new MenuItem("Odds of Winning");
        MenuItem exitItem = new MenuItem("Exit Game");

        menu.getItems().addAll(rulesItem, oddsItem, exitItem);
        menuBar.getMenus().add(menu);


        menuBar.setStyle("-fx-background-color: #906969; -fx-font-size: 20px; -fx-font-weight: bold;");

        Label welcomeLabel = new Label("Welcome To KENO");
        welcomeLabel.setFont(new Font("Courier New Bold", 70));
        welcomeLabel.setTextFill(Color.WHITE);

        Button playButton = new Button("PLAY");
        playButton.setFont(Font.font("Courier New Bold",50));
        playButton.setPrefWidth(150);
        playButton.setPrefHeight(50);
        playButton.setStyle("-fx-background-color: #A20000; -fx-text-fill: white; -fx-font-weight: bold;");
        playButton.setOnAction(e -> showGameScene());

        Image pic = new Image(getClass().getResourceAsStream("/powerballs.png"));
        ImageView imageView = new ImageView(pic);
        imageView.setFitWidth(400);
        imageView.setFitHeight(250);
        imageView.setPreserveRatio(true);

        VBox centerBox = new VBox(25, welcomeLabel, playButton, imageView);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(centerBox);
        root.setStyle("-fx-background-color: #AD9797;");
        BorderPane.setAlignment(centerBox, Pos.CENTER);

        welcomeScene = new Scene(root, 1000, 800);
        primaryStage.setScene(welcomeScene);
        primaryStage.show();

        rulesItem.setOnAction(e -> showRulesPopup());
        oddsItem.setOnAction(e -> showOddsPopup());
        exitItem.setOnAction(e -> primaryStage.close());
    }


    //rules
    private void showRulesPopup() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("KENO Rules");

        Label rulesLabel = new Label(
                "Rules of KENO:\n\n" +
                        "1. Pick how many spots to play (1,4,8 or 10).\n\n" +
                        "2. Pick how many drawings you'll play in bet card for (minimum of 1 and maximum of 4). This can not change once the drawings begin.\n\n" +
                        "3. Once spots are decided, you may select numbers of choice.\n\n" +
                        "4. The more numbers you match, the more you win!"
        );
        rulesLabel.setWrapText(true);
        rulesLabel.setFont(new Font("Courier New Bold", 16));

        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        closeButton.setOnAction(e -> popup.close());

        VBox layout = new VBox(15, rulesLabel, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f2d5d5; -fx-padding: 20;");

        Scene scene = new Scene(layout, 500, 450);
        popup.setScene(scene);
        popup.showAndWait();
    }

    //odds
    private void showOddsPopup() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Odds of Winning");

        Label title = new Label("KENO Odds of Winning");
        title.setFont(new Font("Courier New Bold", 27));
        title.setTextFill(Color.DARKRED);

        //1 spot info
        Label oneSpotTitle = new Label("1-Spot Game");
        oneSpotTitle.setFont(new Font("Courier New Bold", 16));
        oneSpotTitle.setTextFill(Color.DARKRED);

        Label oneSpotTable = new Label(
                "Match\tPrize\tOdds\n" +
                        "1\t$2.00\t4\n" +
                        "\nOverall odds 1 in 4.0"
        );
        oneSpotTable.setFont(new Font("Courier New", 14));

        //4 spot info
        Label fourSpotTitle = new Label("4-Spot Game");
        fourSpotTitle.setFont(new Font("Courier New Bold", 16));
        fourSpotTitle.setTextFill(Color.DARKRED);

        Label fourSpotTable = new Label(
                "Match\tPrize\tOdds\n" +
                        "4\t$75\t319\n" +
                        "3\t$5\t24\n" +
                        "2\t$1\t5\n" +
                        "\nOverall odds 1 in 3.86"
        );
        fourSpotTable.setFont(new Font("Courier New", 14));

        //8 spot info
        Label eightSpotTitle = new Label("8-Spot Game");
        eightSpotTitle.setFont(new Font("Courier New Bold", 16));
        eightSpotTitle.setTextFill(Color.DARKRED);

        Label eightSpotTable = new Label(
                "Match\tPrize\tOdds\n" +
                        "8\t$10K\t230,115\n" +
                        "7\t$750\t6,233\n" +
                        "6\t$50\t423\n" +
                        "5\t$12\t55\n" +
                        "4\t$2\t13\n" +
                        "\nOverall odds 1 in 9.77"
        );
        eightSpotTable.setFont(new Font("Courier New", 14));

        //10 spot info
        Label tenSpotTitle = new Label("10-Spot Game");
        tenSpotTitle.setFont(new Font("Courier New Bold", 16));
        tenSpotTitle.setTextFill(Color.DARKRED);

        Label tenSpotTable = new Label(
                "Match\tPrize\tOdds\n" +
                        "10\t$100K\t8,911,712\n" +
                        "9\t$4,250\t163,382\n" +
                        "8\t$450\t7,385\n" +
                        "7\t$40\t621\n" +
                        "6\t$15\t88\n" +
                        "5\t$2\t20\n" +
                        "0\t$5\t22\n" +
                        "\nOverall odds 1 in 9.05"
        );
        tenSpotTable.setFont(new Font("Courier New", 14));

        //close button
        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        closeButton.setOnAction(e -> popup.close());

        //table layout
        VBox layout = new VBox(15,
                title,
                oneSpotTitle, oneSpotTable,
                fourSpotTitle, fourSpotTable,
                eightSpotTitle, eightSpotTable,
                tenSpotTitle, tenSpotTable,
                closeButton
        );
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #f2d5d5; -fx-padding: 20;");
        layout.setPrefWidth(450);

        Scene scene = new Scene(layout);
        popup.setScene(scene);
        popup.showAndWait();
    }

    //game screen
    private void showGameScene() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");

        MenuItem rulesItem = new MenuItem("Rules");
        MenuItem oddsItem = new MenuItem("Odds of Winning");
        MenuItem newLookItem = new MenuItem("New Look");
        MenuItem exitItem = new MenuItem("Exit Game");

        menu.getItems().addAll(rulesItem, oddsItem, newLookItem, exitItem);
        menuBar.getMenus().add(menu);

        //play slip
        Label instructions = new Label("Start Play Slip");
        instructions.setFont(new Font("Courier New Bold", 24));
        instructions.setTextFill(Color.BLACK);

        Label spotsLabel = new Label("Select number of spots (1, 4, 8, or 10):");
        ComboBox<Integer> spotsCombo = new ComboBox<>();
        spotsCombo.getItems().addAll(1, 4, 8, 10);
        spotsCombo.setPromptText("Choose spots");

        Label drawingsLabel = new Label("Select number of drawings (1–4):");
        ComboBox<Integer> drawingsCombo = new ComboBox<>();
        drawingsCombo.getItems().addAll(1, 2, 3, 4);
        drawingsCombo.setPromptText("Choose drawings");

        Button quickPickBtn = new Button("Quick Pick Numbers");
        Button startDrawingBtn = new Button("Start Drawing");
        startDrawingBtn.setDisable(true);

//        continueButton.setFont(new Font("Courier New Bold", 16));

        VBox controlsBox = new VBox(15, instructions, spotsLabel, spotsCombo, drawingsLabel, drawingsCombo, quickPickBtn, startDrawingBtn);
        controlsBox.setAlignment(Pos.TOP_LEFT);
        controlsBox.setStyle("-fx-padding: 20; -fx-background-color: #e8caca;");

        //grid format
        GridPane betCard = new GridPane();
        betCard.setHgap(5);
        betCard.setVgap(5);
        betCard.setAlignment(Pos.CENTER);

        for (int i = 0; i < 80; i++) {
            int num = i + 1;
            Button btn = new Button(String.valueOf(num));
            btn.setPrefSize(60, 40);
            btn.setDisable(true);
            btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black; -fx-font-weight: bold;");

            btn.setOnAction(e -> {
                if (btn.isDisabled()) return;
                int maxSpots = spotsCombo.getValue() == null ? 0 : spotsCombo.getValue();

                if (selectedNumbers.contains(num)) {
                    selectedNumbers.remove(Integer.valueOf(num));
                    btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black; -fx-font-weight: bold;");
                } else if (selectedNumbers.size() < maxSpots) {
                    selectedNumbers.add(num);
                    btn.setStyle("-fx-background-color: #b00f0f; -fx-text-fill: white; -fx-font-weight: bold;");
                }
            });

            betCard.add(btn, i % 10, i / 10);
            numberButtons[i] = btn;
        }

        StackPane centerPane = new StackPane(betCard);

        //status&winnings
        Label statusLabel = new Label("Select number of spots to begin.");
        statusLabel.setFont(new Font("Courier New", 16));
        statusLabel.setTextFill(Color.DARKRED);
        statusLabel.setAlignment(Pos.CENTER);

        winningsLabel = new Label("Total winnings: $0");
        winningsLabel.setFont(new Font("Courier New Bold", 16));
        winningsLabel.setTextFill(Color.DARKGREEN);

        continueButton = new Button("Continue to Next Drawing");
        continueButton.setFont(new Font("Courier New Bold", 16));
        continueButton.setStyle("-fx-background-color: #b00f0f; -fx-text-fill: white;");
        continueButton.setVisible(false);


        continueButton.setOnAction(e -> {
            if (currentDraw < totalDrawings) {
                continueButton.setVisible(false);
                statusLabel.setText("Drawing " + (currentDraw + 1) + " of " + totalDrawings + "...");
                currentDraw++;

                startSingleDrawing(
                        spotsCombo.getValue(),
                        statusLabel,
                        winningsLabel,
                        spotsCombo,
                        drawingsCombo,
                        quickPickBtn,
                        startDrawingBtn,
                        bottomBox
                );
            } else {
                statusLabel.setText("All drawings complete! Final total: $" + totalWinnings);
                continueButton.setDisable(true);
            }
        });


        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setLeft(controlsBox);
        root.setCenter(centerPane);
//        root.setBottom(bottomBox);
        root.setStyle("-fx-background-color: #AD9797;");

        bottomBox = new HBox(20, statusLabel, winningsLabel, continueButton);
        bottomBox.setAlignment(Pos.CENTER);
        root.setBottom(bottomBox);

        gameScene = new Scene(root, 1000, 800);
        primaryStage.setScene(gameScene);

        //menu act
        rulesItem.setOnAction(e -> showRulesPopup());
        oddsItem.setOnAction(e -> showOddsPopup());
        exitItem.setOnAction(e -> primaryStage.close());
        newLookItem.setOnAction(e -> toggleNewLook(root, controlsBox, statusLabel));


        spotsCombo.setOnAction(e -> {
            if (spotsCombo.getValue() != null) {
                statusLabel.setText("You selected " + spotsCombo.getValue() + " spots. Now choose drawings.");
                for (Button btn : numberButtons) btn.setDisable(false);
                selectedNumbers.clear();
                for (Button btn : numberButtons)
                    btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black; -fx-font-weight: bold;");
            }
        });

        drawingsCombo.setOnAction(e -> {
            if (spotsCombo.getValue() != null && drawingsCombo.getValue() != null) {
                statusLabel.setText("Ready! You chose " + spotsCombo.getValue() +
                        " spots and " + drawingsCombo.getValue() + " drawings.");
                startDrawingBtn.setDisable(false);
            }
        });

        quickPickBtn.setOnAction(e -> {
            if (spotsCombo.getValue() == null) {
                statusLabel.setText("Please select number of spots first!");
                return;
            }

            int spots = spotsCombo.getValue();
            selectedNumbers.clear();

            for (Button btn : numberButtons) {
                btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black; -fx-font-weight: bold;");
            }

            Set<Integer> picks = KenoLogic.generateQuickPick(spots);

            for (int num : picks) {
                selectedNumbers.add(num);
                numberButtons[num - 1].setStyle("-fx-background-color: #b00f0f; -fx-text-fill: white; -fx-font-weight: bold;");
            }

            statusLabel.setText("Quick Pick selected: " + selectedNumbers);
        });

        startDrawingBtn.setOnAction(e -> {
            if (spotsCombo.getValue() == null) {
                statusLabel.setText("Please select number of spots first!");
                return;
            }
            int requiredSpots = spotsCombo.getValue();
            if (selectedNumbers.size() != requiredSpots) {
                statusLabel.setText("Please select exactly " + requiredSpots + " numbers before starting!");
                return;
            }

            totalDrawings = drawingsCombo.getValue();
            currentDraw = 1;
            totalWinnings = 0;

            for (Button btn : numberButtons) btn.setDisable(true);
            startDrawingBtn.setDisable(true);
            quickPickBtn.setDisable(true);
            statusLabel.setText("Drawing " + currentDraw + " of " + totalDrawings + "...");

//            bottomBox = new HBox(10, statusLabel, winningsLabel, continueButton);
//            bottomBox.setAlignment(Pos.CENTER);

            startSingleDrawing(spotsCombo.getValue(), statusLabel, winningsLabel,
                    spotsCombo, drawingsCombo, quickPickBtn, startDrawingBtn, bottomBox);
        });
    }

    //helper method
    private void startSingleDrawing(int spots, Label statusLabel, Label winningsLabel,
                                    ComboBox<Integer> spotsCombo, ComboBox<Integer> drawingsCombo,
                                    Button quickPickBtn, Button startDrawingBtn, HBox bottomBox){

    List<Integer> drawnNumbers = KenoLogic.generateDraw(20);
        int[] drawnArray = drawnNumbers.stream().mapToInt(Integer::intValue).toArray();
        runDrawingTimeline(new HashSet<>(selectedNumbers), drawnArray,
                statusLabel, winningsLabel, spots,
                spotsCombo, drawingsCombo, quickPickBtn, startDrawingBtn, bottomBox);
        for (Button btn : numberButtons) btn.setDisable(true);

    }

    //new look, purple:)
    private boolean isAltLook = false;

    private void toggleNewLook(BorderPane root, VBox controls, Label status) {
        if (!isAltLook) {
            root.setStyle("-fx-background-color: #C4B9CC;");
            controls.setStyle("-fx-padding: 20; -fx-background-color: #9284B1;");
            status.setTextFill(Color.WHITE);
            isAltLook = true;
        } else {
            root.setStyle("-fx-background-color: #f2d5d5;");
            controls.setStyle("-fx-padding: 20; -fx-background-color: #e8caca;");
            status.setTextFill(Color.DARKRED);
            isAltLook = false;
        }
    }


    private void runDrawingTimeline(Set<Integer> selectedNumbers, int[] drawnNumbers, Label statusLabel, Label winningsLabel, int spots, ComboBox<Integer> spotsComboRef, ComboBox<Integer> drawingsComboRef, Button quickPickRef, Button startBtnRef, HBox bottomBoxRef) {
        Timeline timeline = new Timeline();
        Duration pause = Duration.seconds(1);

        for (int i = 0; i < drawnNumbers.length; i++) {
            final int index = i;
            final int number = drawnNumbers[i];
            KeyFrame keyFrame = new KeyFrame(pause.multiply(index + 1), e -> {
                Button btn = numberButtons[number - 1];
                btn.setStyle("-fx-background-color: #00b0f0; -fx-text-fill: white; -fx-font-weight: bold;");

                statusLabel.setText("Number drawn: " + number);

                if (selectedNumbers.contains(number)) {
                    btn.setStyle("-fx-background-color: #00ff00; -fx-text-fill: black; -fx-font-weight: bold;");
                }

                if (index == drawnNumbers.length - 1) {
                    int winnings = calculateWinnings(selectedNumbers, drawnNumbers, spots);
                    totalWinnings += winnings;
                    winningsLabel.setText("Total winnings: $" + totalWinnings);
                    statusLabel.setText("Drawing " + currentDraw + " complete! You won $" + winnings + " this round.");

                    if (currentDraw < totalDrawings) {
//                        currentDraw++;
                        continueButton.setVisible(true);
                    } else {
                        statusLabel.setText("All drawings complete! Final total: $" + totalWinnings);
                        continueButton.setDisable(true);
                        Button playAgainBtn = new Button("Play Again");
                        playAgainBtn.setStyle("-fx-background-color: #B00F0F; -fx-text-fill: white; -fx-font-weight: bold;");

                        playAgainBtn.setOnAction(ev -> resetGame(
                                spotsComboRef, drawingsComboRef, quickPickRef, startBtnRef,
                                numberButtons, statusLabel, winningsLabel, bottomBoxRef
                        ));
                        bottomBoxRef.getChildren().add(playAgainBtn);
                    }
                }
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.play();
    }

    private int countMatches(Set<Integer> selectedNumbers, int[] drawnNumbers) {
        int matches = 0;
        for (int num : selectedNumbers) {
            for (int drawn : drawnNumbers) {
                if (num == drawn) matches++;
            }
        }
        return matches;
    }


    private int calculateWinnings(Set<Integer> selectedNumbers, int[] drawnNumbers, int spots) {
        int matches = 0;
        for (int num : selectedNumbers) {
            for (int drawn : drawnNumbers) {
                if (num == drawn) matches++;
            }
        }

        //payout
        switch (spots) {
            case 1:
                if (matches == 1) return 2;
                break;
            case 4:
                switch (matches) {
                    case 4: return 75;
                    case 3: return 5;
                    case 2: return 1;
                    default: return 0;
                }
            case 8:
                switch (matches) {
                    case 8: return 10000;
                    case 7: return 750;
                    case 6: return 50;
                    case 5: return 12;
                    case 4: return 2;
                    default: return 0;
                }
            case 10:
                switch (matches) {
                    case 10: return 100000;
                    case 9: return 4250;
                    case 8: return 450;
                    case 7: return 40;
                    case 6: return 15;
                    case 5: return 2;
                    case 0: return 5;
                    default: return 0;
                }
            default:
                return 0;
        }

        return 0;
    }



    //reset user can play again without exiting game
    private void resetGame(ComboBox<Integer> spotsCombo, ComboBox<Integer> drawingsCombo,
                           Button quickPickBtn, Button startDrawingBtn, Button[] numberButtons,
                           Label statusLabel, Label winningsLabel, HBox bottomBox) {
        selectedNumbers.clear();
        for (Button btn : numberButtons) {
            btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black; -fx-font-weight: bold;");
            btn.setDisable(true);
        }
        spotsCombo.setValue(null);
        drawingsCombo.setValue(null);
        quickPickBtn.setDisable(false);
        startDrawingBtn.setDisable(true);
        statusLabel.setText("Select number of spots to begin.");
        winningsLabel.setText("Total winnings: $0");

        bottomBox.getChildren().removeIf(node -> node instanceof Button && ((Button) node).getText().equals("Play Again"));
    }
}
