import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Task3 {
    private static final String CATALOG_NAME = "t3_katalog";

    public Task3() {
    }

    public static void main(String[] args) {
        task3();
    }

    private static void task3() {
        List<String> fileNames = Arrays.asList("a.txt", "b.txt", "c.txt", "d.txt", "e.txt", "f.txt", "g.txt", "h.txt");
        Iterator var1 = fileNames.iterator();

        while(var1.hasNext()) {
            String fileName = (String)var1.next();

            try {
                Path old = Paths.get("t3_katalog", fileName);
                String newFileName = fileNames.indexOf(fileName) + 1 + " " + fileName;
                Path updated = Paths.get("t3_katalog", newFileName);
                Files.move(old, updated, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(fileName + " Успешно переименован в " + newFileName);
            } catch (DirectoryNotEmptyException var6) {
                System.out.println("Не удалось переименовать Файл " + fileName + ", ошибка");
                System.out.println(var6.getMessage());
            } catch (IOException var7) {
                System.out.println("Не удалось переименовать Файл, ошибка");
                System.out.println("Что-то не так с " + fileName);
                System.out.println(var7.getMessage());
            }
        }

    }
}
