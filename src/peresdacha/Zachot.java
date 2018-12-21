package peresdacha;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ljavafx.L7_ResourcesExamples;

import java.awt.*;
import java.io.InputStream;


public class Zachot extends Application {

    private Label title = new Label("здесь будут вопросы");
    private ImageView picture = new ImageView();

    private int questionNumber = 0;
    private Question[] questions = new Question[5];
    private int result = 0;


    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Ослиные вопросы");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }

    private Parent initInterface() {
        VBox root = new VBox();

        InputStream pickURL = L7_ResourcesExamples.class.getResourceAsStream("ослик.jpg");
        Image img = new Image(pickURL);
        picture.setImage(img);
        root.getChildren().add(picture);

        return root;

    }
}
