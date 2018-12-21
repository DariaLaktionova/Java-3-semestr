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
import java.util.Arrays;
import java.util.Scanner;

public class Zachot extends Application {
    private int width = 100;
    private int heigh = 100;

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
        title.setText("Где левый верхний собачный угол");
        zagrImage();
        zagrQuest();
        return new VBox(title, picture);
    }

    private void initInteraction(Stage primaryStage) {

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
                InputStream text = Zachot.class.getResourceAsStream("a.txt")
        ) {
            Scanner scannerIn = new Scanner(text);

            for (int i = 0; i < 5; i++) {
                questions[i] = new Question(
                        scannerIn.nextInt(), scannerIn.nextInt(), scannerIn.nextLine()
                );
            }

        } catch (Exception e) {
            System.out.println("failed to read the resource with questions");
            e.printStackTrace();
        }
    }

    private void clicked(double x, double y) {
        Question question = questions[questionNumber];
        if ( question.getX() <= x && x <= question.getX() + width &&
                question.getY() <= y && y <= question.getY() + heigh)
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
