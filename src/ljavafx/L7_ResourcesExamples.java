package ljavafx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Ресурсы - файлы, которые распространяются с программой. Они "пакуются"
 * внутрь запускаемого файла. В Java ресурсы нужно хранить в каталоге
 * с исходниками. Ну или в отдельном специальном каталоге, который
 * можно отдельно настроить для хранения ресурсов.
 * В Java ресурсы, как и классы, принадлежат пакетам, поэтому важно,
 * в какое место внутри каталога src их положили. Обычно их кладут
 * в тот же пакет, что и у класса, который их использует
 */

/*
 * Jar файлы - это аналог запускаемых файлов типа ехе в Виндоус
 * Они содержат внутри себя
 *  - скомпилированные классы (.class файлы)
 *  - ресурсы
 *  - файл манифеста. описывает метаинформацию, например,
 *  кто автор приложения, версия приложения
 *  может описывать запускаемый класс с main методом
 *  Может содержать ссылки на библиотеки, если
 *  эти библиотеки ен находятся сами внутри jar
 *
 *  Фактически, jar файл - это zip архив, при желании
 * его можно даже создавать вручную, архивируя содержимое
 *
 * jar = java archive
 * jar переводится как баночка, кувшин.
 *  project structure -> artifacts -> create JAR from modules ->
 *  выбираем модуль и мэин класс
 */


public class L7_ResourcesExamples extends Application {

    private Label title;
    private ImageView picture;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Пример про работу с ресурсами");
        Parent root = initInterface();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        loadText();
        loadImage();



    }

    private Parent initInterface() {
        //У нас будет два компонента: ImageView ддля отображения картинки
        //и Label под ним для отображения текста
        //Картинка и текст будут взяты из ресурсов
        picture = new ImageView();
        title = new Label();
        return new VBox(picture, title);

        //ресурсы - файлы, которые распространяются вместе с программой
    }

    private void loadText () {
        //как же получить доступ к ресурсам
        //1) К ресурсу можно получить доступ через InputStream
        try (
                InputStream text = L7_ResourcesExamples
                        .class //это доступ к самому классу (см. рефлексия)
                        .getResourceAsStream("L7_title.jp") //ресурс из того же пакета,
                //что и сам класс
        ){
            //рассчитываем, тчо хватит 1024 байт для чтения текста
            byte[] bytesFromInputStream = new byte[1024];
            //возвратит, сколько прочитано
            int read = text.read(bytesFromInputStream);
            //превращаем набор байт в текст, указываем, сколько байт брать
            //из массива
            String titleText = new String(
                    bytesFromInputStream,
                    0, read,
                    UTF_8);
            //да, неудобно, что при чтении ресурса мы получаем IS, но так исторически сложилось
            //пользуемся тем, что есть. в других ситуациях будут
            //вспомогательне методы для чтения IS

            title.setText(titleText);

        } catch (IOException e) {
            title.setText("не удалось загрузить текст");
        }

    }

    private void loadImage () {
        //загружаем картинку javafx.scene.Image
        try (
                InputStream image = L7_ResourcesExamples
                        .class
                        .getResourceAsStream("cat.jpg")

        ){
            //картинку можно созать напрямую из IS
            Image img = new Image(image);
            picture.setImage(img);

        } catch (IOException e) {
            //ничего не делаем, просто не будем загружать и все
        }

        //2) доступ к ресурсу можно получить еще и через URL
        URL pickURL = L7_ResourcesExamples.class.getResource("cat.jpg");
        System.out.println("url: " + pickURL);
        //картинку можено создавать и на основе URL, только надо привести к String;
        new Image(pickURL.toExternalForm());

    }
}