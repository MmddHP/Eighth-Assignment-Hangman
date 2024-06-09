package hangman;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HangmanController implements Initializable {
    @FXML
    private String guess;
    @FXML
    private HBox blanks;
    @FXML
    private Label topic;
    @FXML
    private AnchorPane drawingPane;
    @FXML
    private AnchorPane playPane;
    @FXML
    private Label announse;
    @FXML
    private Button menuButton;
    @FXML
    private Button playAgain;
    @FXML
    private Group pauseMenu;
    @FXML
    private Button pauseButton;
    @FXML
    private ImageView loseGif;
    @FXML
    private ImageView winGif;
    @FXML
    private Group menu;
    @FXML
    private AnchorPane logInPane;
    @FXML
    private AnchorPane signUpPane;
    @FXML
    private Label timeLabel;

    private long startTime;
    private Timeline timeline;
    private boolean running = false;
    private long pausedTime; // To track the time when paused
    private long totalElapsedTime;
    private int wrongGuesses = 0;
    private String wordTopic = "Fruit";
    private String wordToGuess = "APPLE";
    private static boolean shouldInitialize = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!shouldInitialize)
            return;
        playAgain.setOpacity(0);
        menuButton.setOpacity(0);
        timeLabel.setText("00:00");
        startTimer();
        resumeTimer();
        topic.setText(wordTopic);
        for(int i = 0; i < wordToGuess.length(); i++){
            Label label = new Label("━");
            label.setId(String.valueOf(wordToGuess.charAt(i)));
            label.setPrefWidth(56);
            label.setPrefHeight(51);
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(24));
            blanks.getChildren().add(label);
        }
    }

    public void keyboardClicked(ActionEvent event) {
        guess = ((Button)event.getSource()).getText();
        boolean trueGuess = false;
        for(int i = 0; i < wordToGuess.length(); i++){   //guess check and update
            if(Objects.equals(guess, String.valueOf(wordToGuess.charAt(i)))){
                ((Label)(blanks.getChildren().get(i))).setText(guess);
                trueGuess = true;
            }
        }

        if(trueGuess){
            ((Button)event.getSource()).setStyle("-fx-background-color: #27f827");
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), ((Button)event.getSource()));
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0.2);
            fadeTransition.play();
        } else{
            wrongGuesses++;
            ((Button)event.getSource()).setStyle("-fx-background-color: #fb2222");
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), ((Button)event.getSource()));
            fadeTransition.setFromValue(1);
            fadeTransition.setToValue(0.2);
            fadeTransition.play();
        }
        switch (wrongGuesses){
            case 1:{
                Line line = new Line(17,-60,17,60);
                line.setLayoutX(83);
                line.setLayoutY(62);
                line.setStrokeWidth(3.0);
                drawingPane.getChildren().add(line);

                animateLine(line, 17, -60, 17, 16, 1);
                break;
            }
            case 2:{
                Circle circle = new Circle(100, 108, 28); // Center horizontally and vertically in the Pane
                circle.setStroke(Color.BLACK);
                circle.setStrokeWidth(3.0);
                circle.setOpacity(0); // Initially fully transparent
                drawingPane.getChildren().add(circle);
                ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResourceAsStream("mahan1.png")));
                circle.setFill(imagePattern);
                // Create a FadeTransition
                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), circle);
                fadeTransition.setFromValue(0); // Start fully transparent
                fadeTransition.setToValue(1); // End fully opaque
                fadeTransition.setCycleCount(1); // Run the animation once
                fadeTransition.setAutoReverse(false); // Do not reverse the animation

                fadeTransition.play();
                break;
            }
            case 3:{
                Line body = new Line(9, -47, 9, 48);
                body.setLayoutX(91);
                body.setLayoutY(182);
                body.setStrokeWidth(3.0);
                drawingPane.getChildren().add(body);
                //drawing body
                animateLine(body, 9, -47, 9, 48, 1);
                break;
            }
            case 4:{
                Line line = new Line(172.5, 257.5, 141, 313);
                line.setLayoutX(-75);
                line.setLayoutY(-120);
                line.setStrokeWidth(3.0);
                drawingPane.getChildren().add(line);
                //left hand
                animateLine(line, 172.5, 257.5, 141, 313, 1);
                break;
            }
            case 5:{
                Line line = new Line(-99, -25, -67, 31);
                line.setLayoutX(202);
                line.setLayoutY(163);
                line.setStrokeWidth(3.0);
                drawingPane.getChildren().add(line);
                //right hand
                animateLine(line, -99, -25, -67, 31, 1);
                break;
            }
            case 6:{
                Line line = new Line(71.5, -38.5, 40, 35);
                line.setLayoutX(27);
                line.setLayoutY(268);
                line.setStrokeWidth(3.0);
                drawingPane.getChildren().add(line);
                //left leg
                animateLine(line, 71.5, -38.5, 40, 35, 1);
                break;
            }
            case 7:{
                Line line = new Line(63.20709228515625, -30.20709228515625, 94, 42);
                line.setLayoutX(38);
                line.setLayoutY(258);
                line.setStrokeWidth(3.0);
                drawingPane.getChildren().add(line);
                //right leg
                animateLine(line, 63.20709228515625, -30.20709228515625, 94, 42, 1);
                break;
            }
        }
        boolean isWinner = true;
        if(wrongGuesses == 7){
            pauseTimer();
            isWinner = false;
            endGameMenu(false);
        } else {
            for (Node node : blanks.getChildren()) {
                Label word = (Label) node;
                if (!Objects.equals(word.getText(), word.getId())) {
                    isWinner = false;
                    break;
                }
            }
        }
        if(isWinner){
            pauseTimer();
            endGameMenu(isWinner);
        }
    }

    private void animateLine(Line line, double startX, double startY, double endX, double endY, int durationSeconds) {
        // Define key frames
        KeyFrame start = new KeyFrame(Duration.ZERO,
                new KeyValue(line.endXProperty(), startX),
                new KeyValue(line.endYProperty(), startY)
        );

        KeyFrame end = new KeyFrame(Duration.seconds(durationSeconds),
                new KeyValue(line.endXProperty(), endX),
                new KeyValue(line.endYProperty(), endY)
        );

        // Create a timeline with the key frames
        Timeline timeline = new Timeline(start, end);

        // Start the animation
        timeline.play();
    }

    private void endGameMenu(boolean isWinner){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), playPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.25);

        fadeTransition.play();
        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(3), pauseButton);
        fadeTransition2.setFromValue(1);
        fadeTransition2.setToValue(0.25);

        fadeTransition2.play();

        FadeTransition fadeTransition3 = new FadeTransition(Duration.seconds(3), timeLabel);
        fadeTransition3.setFromValue(1);
        fadeTransition3.setToValue(0.25);

        fadeTransition3.play();

        playPane.setDisable(true);
        pauseButton.setDisable(true);

        if(!isWinner){
            announse.setText("Game Over!");
            loseGif.setVisible(true);
        }
        if(isWinner){
            announse.setText("You Win!");
            winGif.setVisible(true);
        }

        FadeTransition fade = new FadeTransition(Duration.seconds(3), announse);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        FadeTransition fadePlayPane = new FadeTransition(Duration.seconds(3), playAgain);
        fadePlayPane.setFromValue(0);
        fadePlayPane.setToValue(1);
        fadePlayPane.play();
        playAgain.setOpacity(1);

        FadeTransition fadeMenuButton = new FadeTransition(Duration.seconds(3), menuButton);
        fadeMenuButton.setFromValue(0);
        fadeMenuButton.setToValue(1);
        fadeMenuButton.play();
        menuButton.setOpacity(1);

    }

    public void pause(){
        pauseTimer();
        pauseMenu.setVisible(true);
        playPane.setDisable(true);
    }

    public void resume(){
        resumeTimer();
        pauseMenu.setVisible(false);
        playPane.setDisable(false);
    }

    public void menuInHangmanGame(ActionEvent event) throws IOException {
        setShouldInitialize(false);
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(HangmanApp.class.getResource("log-in.fxml"));
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img_2.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        switchToMenu();
        primaryStage.show();
        setShouldInitialize(true);
    }

    public void playAgain(ActionEvent event) throws IOException {
        HangmanController.setShouldInitialize(true);
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HangmanApp.class.getResource("hangman-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm();
        primaryStage.setTitle("Hangman");
        scene.getStylesheets().add(css);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img_2.png")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void logIn(ActionEvent event) {
        logInPane.setVisible(false);
        menu.setOpacity(0);
        menu.setVisible(true);
        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2), menu);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();
        menu.setOpacity(1);

    }

    public void signUp(ActionEvent event) throws IOException {
        signUpPane.setVisible(false);
        menu.setOpacity(0);
        menu.setVisible(true);
        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2), menu);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();
        menu.setOpacity(1);

    }

    public void switchToSignUp() {
        logInPane.setVisible(false);
        signUpPane.setOpacity(0);
        signUpPane.setVisible(true);
        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2), signUpPane);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();
        signUpPane.setOpacity(1);

    }

    public void switchToLogIn() {
        signUpPane.setVisible(false);
        logInPane.setOpacity(0);
        logInPane.setVisible(true);
        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(2), logInPane);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(1);
        fadeTransition2.play();
        logInPane.setOpacity(1);

    }

    public void switchToMenu() {
        logInPane.setVisible(false);
        menu.setVisible(true);
    }

    public static void setShouldInitialize(boolean shouldInitialize) {
        HangmanController.shouldInitialize = shouldInitialize;
    }

    public void exit(ActionEvent event) throws IOException {
        HangmanController.setShouldInitialize(false);
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HangmanApp.class.getResource("log-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm();
        primaryStage.setTitle("Hangman");
        scene.getStylesheets().add(css);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("img_2.png"))));
        primaryStage.setScene(scene);
        primaryStage.show();
        HangmanController.setShouldInitialize(true);
    }

    public void startTimer() {
        running = false;

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (running) {
                        long elapsedTime = System.currentTimeMillis() - startTime + totalElapsedTime ;
                        long totalSeconds = elapsedTime / 1000;
                        long minutes = (totalSeconds % 3600) / 60;
                        long seconds = totalSeconds % 60;
                        timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
                    }
                })
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void pauseTimer() {
        if (running) {

            long currentTime = System.currentTimeMillis();
            totalElapsedTime += (currentTime - startTime);
            running = false;
            if (timeline != null) {
                timeline.stop();
            }
        }
    }

    public void resumeTimer() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
            timeline.play();
        }
    }
}