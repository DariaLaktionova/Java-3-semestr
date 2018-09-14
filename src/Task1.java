public class Task1 {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            try {
                f(i);
            } catch (Exception e) {
                System.out.println(i + " - есть исключение");
            }
            if (i % 2 != 0)
                System.out.println(i + " - нет исключения");
        }

    }

    private static void f(int x) throws Exception {
        //функция чётная - исключение, нечётная - ничего
        if (x % 2 == 0)
            throw new Exception();

    }

}
