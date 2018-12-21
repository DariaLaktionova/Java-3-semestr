package ljavafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;

public class ImageGallery extends Application {

    private SplitPane root = new SplitPane();
    private ListView<File> images = new ListView<>();
    private Button chooseButton = new Button("выбрать");
    private TextField pathToCatalog = new TextField("путь до каталога");

    private ImageView fullImage = new ImageView();
    private Pane paneForImageView = new Pane(fullImage);

    private ObservableList<File> listOfImages = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("изображения");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));

        initInteraction();
        primaryStage.show();
    }

    private Parent initInterface() {

        HBox chooseAndPath = new HBox(pathToCatalog, chooseButton);

        chooseAndPath.setAlignment(Pos.TOP_RIGHT);
        HBox.setHgrow(pathToCatalog, Priority.ALWAYS);

        VBox rightSide = new VBox(chooseAndPath, paneForImageView);


        VBox.setVgrow(paneForImageView, Priority.ALWAYS);
        HBox.setHgrow(paneForImageView, Priority.ALWAYS);
        fullImage.fitWidthProperty().bind(paneForImageView.widthProperty());
        fullImage.fitHeightProperty().bind(paneForImageView.heightProperty());
        fullImage.setPreserveRatio(true);

        fullImage.setImage(new Image(L7_ResourcesExamples.class.getResource("ослик.jpg").toExternalForm()));

        root.getItems().addAll(images, rightSide);

        return root;
    }

    private void initInteraction() {

        //Для начала выберите какой-то фиксированный каталог с изображениями,
        String ImageDirectory = "C:\\Users\\Институт\\Desktop\\видеомонтаж\\билл";
        File dirFile = new File(ImageDirectory);
        
        // напишите код, который находит все файлы в этом каталоге
        if (dirFile.isDirectory()) {
            File[] imageFiles = dirFile.listFiles();
            if (imageFiles != null) {
                listOfImages.addAll(Arrays.asList(imageFiles));
                // и добавляет их в список ObservableList.
            }
        }
        images.setItems(listOfImages);
        
        //Сделайте так, что при выборе изображения в списке (images), оно бы отображалось в ImageView (fullImage).
        images.getSelectionModel().selectedItemProperty().addListener(
                prop -> {
                    File selectedImage = images.getSelectionModel().getSelectedItem();
                    String imageURL = selectedImage.toURI().toString();
                    Image img = new Image(imageURL);
                    fullImage.setImage(img);
                }
        );
        
        //сделайте так, что ListView (images) при отображении элемента показывает картинку и имя файла.
        images.setCellFactory(
                (lv) -> new ListCell<File>() { // lv - listviewer
                    @Override
                    protected void updateItem(File item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty)
                            setText("");
                        else {
                            String i = item.toString();
                            setText(i.substring(ImageDirectory.length() + 1));

                            String imageURL = item.toURI().toString();
                            Image img = new Image(imageURL, 64, 64, true, false);
                            setGraphic(new ImageView(img));
                        }
                    }
                });
    }
}