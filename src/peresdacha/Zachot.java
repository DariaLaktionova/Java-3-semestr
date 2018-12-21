package peresdacha;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.Scanner;


public class Zachot extends Application {

    private Label title = new Label("здесь будут вопросы");
    private ImageView picture = new ImageView();

    private int questionNumber = 0;
    private Question[] questions = new Question[5];
    private int result = 0;


    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Собачьи вопросы");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        initInteraction(primaryStage);
        primaryStage.show();
    }

    private Parent initInterface() {
        /*VBox root = new VBox();

        InputStream pickURL = L7_ResourcesExamples.class.getResourceAsStream("ослик.jpg");
        Image img = new Image(pickURL);
        picture.setImage(img);
        root.getChildren().add(picture);

        return root;*/
        title.setText("Где сам осленок?");
        zagrImage();
        title.setFont(Font.font(40));
        return new VBox(title, picture);

    }

    private void initInteraction(Stage primaryStage) {
        zagrQuest();
        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                clicked(event.getX(), event.getY())
        );
    }


    private void zagrImage() {
        InputStream image = Zachot.class.getResourceAsStream("oselik.jpg")
        Image img = new Image(image);
        picture.setImage(img);
    }

    private void zagrQuest() {
        InputStream textIS = Zachot.class.getResourceAsStream("qcoord");
        Scanner scannerIn = new Scanner(textIS);
        for (int i = 0; i < 5; i++) {
            questions[i] = new Question(
                    scannerIn.nextInt(), scannerIn.nextInt(), scannerIn.nextInt(), scannerIn.nextInt(),
                    scannerIn.nextLine()
            );
        }
    }

    private void clicked(int x, int y) {
        Question question = questions[questionNumber];
        if (question.getX() == x && question.getY() == y)
            result++;
        questionNumber++;

        if (questionNumber == 5) {
            new Alert(Alert.AlertType.INFORMATION, "Конец! Итого: " + result + "/" + questionNumber).showAndWait();
            questionNumber = 0;
            result = 0;
        }
        title.setText(questions[questionNumber].getQuestionString());
    }

}
