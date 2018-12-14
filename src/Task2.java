import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        realiz();
    }

    private static int readInt(String str) {
        Scanner s = new Scanner(System.in);
        System.out.println(str);
        while (true) {
            try {
                return Integer.parseInt(s.nextLine()); // Integer.parseInt() - если не число, то исключенеие
            } catch (Exception e) {
                System.out.println("это не число, попробуйте еще раз");
            }
        }
    }

    private static void realiz() {
        int x = readInt("введите x");
        int y = readInt("введите y");
        System.out.println("x + y = " + (x + y));
    }

}

