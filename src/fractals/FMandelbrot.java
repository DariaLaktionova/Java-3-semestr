package fractals;

public class FMandelbrot implements Fractal {
    @Override
    public double getColor(double x, double y) {
        double p = x;
        double q = y;

        int N = 100;
        double R = 100;

        int k = 0;
        for (int i = 0; i < 100; i++) {
            if (x * x + y * y < R * R) {
                double x1 = x * x - y * y + p;
                double y1 = 2 * x * y + q;
                x = x1;
                y = y1;
                k++;
            }
            else {
                return (double)k / N;
            }

        }

        return 1;
    }
}

