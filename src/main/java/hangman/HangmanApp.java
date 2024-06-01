package hangman;

import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class HangmanApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create an ImageView
        ImageView imageView = new ImageView(new Image("path/to/your/image.jpg"));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        // Create a rectangle with rounded corners to use as the clip
        Rectangle clip = new Rectangle(200, 200);
        clip.setArcWidth(30);
        clip.setArcHeight(30);

        // Set the clip to the ImageView
        imageView.setClip(clip);

        // Add the ImageView to the layout
        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 300, 300);

        primaryStage.setTitle("Rounded ImageView");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}