import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class RemoveDuplicateWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите строку с повторяющимися словами: ");
        String input = scanner.nextLine();
        
        String result = removeDuplicates(input);
        
        System.out.println("Результат: " + result);
    }
    
    public static String removeDuplicates(String input) {
        // Разбиваем строку на слова
        String[] words = input.split(" ");
        
        // Используем LinkedHashSet для сохранения порядка и удаления дубликатов
        Set<String> uniqueWords = new LinkedHashSet<>();
        
        // Добавляем слова в набор (дубликаты будут автоматически пропущены)
        for (String word : words) {
            uniqueWords.add(word);
        }
        
        // Собираем результат обратно в строку
        return String.join(" ", uniqueWords);
    }
}