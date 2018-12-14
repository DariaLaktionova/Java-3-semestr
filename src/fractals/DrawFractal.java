package fractals;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.scene.input.KeyCode.*;

public class DrawFractal extends Application {

    private double x0 = -2;
    private double y0 = 2;
    private double dx = 0.01;
    private int windowWidth = 400;
    private int windowHeight = 400;

    private Fractal fractal = new FMandelbrot();
    private Palette palette = new PaletteBlackwhite();

    private Pane root = new Pane();
    private ImageView fullImage = new ImageView(createFractalImage(windowWidth, windowHeight));

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Фракталы");

        Parent root = initInterface();
        primaryStage.setScene(new Scene(root, windowWidth, windowHeight));

        initInteraction();
        primaryStage.show();

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event ->
                changePlace(event.getCode())
        );

    }

    private Parent initInterface() {
        root.getChildren().addAll(fullImage);
        return root;
    }

    private void initInteraction() {
        root.widthProperty().addListener(
                prop -> updateImage()
        );

        root.heightProperty().addListener(
                prop -> updateImage()
        );
    }

    private void changePlace(KeyCode keyCode) {
        int shift = 100;
        if (keyCode == UP)
            y0 += shift * dx;
        if (keyCode == DOWN)
            y0 -= shift * dx;
        if (keyCode == LEFT)
            x0 -= shift * dx;
        if (keyCode == RIGHT)
            x0 += shift * dx;

        if (keyCode == EQUALS || keyCode == ADD) {
            double dx1 = dx / 1.5;
            x0 += root.getWidth()/2 * (dx - dx1);
            y0 -= root.getHeight()/2 * (dx - dx1);
            dx = dx1;
        }

        if (keyCode == MINUS || keyCode == SUBTRACT) {
            double dx1 = dx * 1.5;
            x0 += root.getWidth()/2 * (dx - dx1);
            y0 -= root.getHeight()/2 * (dx - dx1);
            dx = dx1;
        }


        updateImage();
    }

    private void updateImage() {
        if (root.getWidth() != 0 && root.getHeight() != 0)
            fullImage.setImage(createFractalImage((int) root.getWidth(), (int) root.getHeight()));
    }

    private Image createFractalImage(int width, int height) {

        WritableImage wiImageWithFractal = new WritableImage(width, height);
        PixelWriter pixelWriterForImageWithFractal = wiImageWithFractal.getPixelWriter();

        //перебрать все пиксели
        for (int ix = 0; ix < width - 1; ix++) {
            for (int iy = 0; iy < height - 1; iy++) {
                double x = x0 + ix * dx;
                double y = y0 - iy * dx;
                double colorInd = fractal.getColor(x, y);
                Color color = palette.getColor(colorInd);
                pixelWriterForImageWithFractal.setColor(ix, iy, color);
            }

        }


        return wiImageWithFractal;
    }

}


