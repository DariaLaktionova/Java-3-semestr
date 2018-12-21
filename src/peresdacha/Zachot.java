package peresdacha;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
        title.setText("Просто тыкни");
        zagrImage();
        return new VBox(title, picture);
    }

    private void initInteraction(Stage primaryStage) {
        zagrQuest();
        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                clicked(event.getX(), event.getY())
        );
    }


    private void zagrImage() {
        InputStream image = Zachot.class.getResourceAsStream("medeya.jpg");
        Image img = new Image(image);
        picture.setImage(img);
    }


    private void zagrQuest() {
        try (
                InputStream text = Zachot.class.getResourceAsStream("qcoord.txt")
        ) {
            Scanner scannerIn = new Scanner(text);
            for (int i = 0; i < 5; i++) {
                questions[i] = new Question(
                        scannerIn.nextInt(), scannerIn.nextInt(), scannerIn.nextLine()
                );
            }

        } catch (Exception e) {
            //
        }
    }

    private void clicked(double x, double y) {
        Question question = questions[questionNumber];
        System.out.println(question.getX());
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
