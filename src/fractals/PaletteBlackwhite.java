package fractals;

import javafx.scene.paint.Color;

class PaletteBlackwhite implements Palette {
    @Override
    public Color getColor(double ind) {
        if (ind < 0.5)
            return Color.BLACK;
        else
            return Color.WHITE;
    }
}
