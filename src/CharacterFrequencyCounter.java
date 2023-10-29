import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Класс CharacterFrequencyCounter представляет собой программу на Java,
 * которая считает частоту появления каждого символа в файле input.txt,
 * исключая пробелы и переносы строк, и записывает результат в файл output.txt.
 * @author Серявина Софья
 */
public class CharacterFrequencyCounter {
    public static void main(String[] args) throws IOException {
        // считывает содержимое файла input.txt в строку input с помощью метода readAllBytes класса Files и метода get класса Paths.
        String inputFileName = "input.txt";
        String input = new String(Files.readAllBytes(Paths.get(inputFileName)));

        // создает отображение frequency типа HashMap, которое будет использоваться для хранения частоты появления каждого символа.
        Map<Character, Integer> frequency = new HashMap<>();
        // проходит по строке input символ за символом с помощью метода toCharArray, который возвращает массив символов.
        for (char c : input.toCharArray()) {
            //проверяет, является ли символ пробелом или переносом строки с помощью метода isWhitespace класса Character.
            if (!Character.isWhitespace(c)) {
                // увеличивает частоту появления символа c в отображении frequency с помощью метода put,
                // если символ уже присутствует в отображении, или с помощью метода getOrDefault, если символ отсутствует в отображении.
                frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            }
        }

        // сортирует список sorted по частоте в порядке убывания с помощью метода comparingByValue класса Entry и метода reversed.
        List<Map.Entry<Character, Integer>> sorted = new ArrayList<>(frequency.entrySet());
        sorted.sort(Map.Entry.<Character, Integer>comparingByValue().reversed());

        // объявляет строковую переменную outputFileName и присваивает ей значение "output.txt", которое представляет имя выходного файла.
        String outputFileName = "output.txt";
        PrintWriter writer = new PrintWriter(outputFileName);
        // проходит по списку sorted элемент за элементом с помощью цикла for-each.
        for (Map.Entry<Character, Integer> entry : sorted) {
            writer.print("'" + entry.getKey() + "' = " + entry.getValue() + ", ");
        }
        writer.close();
    }
}