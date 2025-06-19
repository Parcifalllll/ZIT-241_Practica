import java.util.Scanner;

public class TextTransformer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст:");
        String input = scanner.nextLine();
        
        String transformedText = transformText(input);
        System.out.println("Результат:");
        System.out.println(transformedText);
    }
    
    public static String transformText(String text) {
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == 0) continue;
            
            String word = words[i];
            boolean hasPunctuation = false;
            char punctuation = ' ';
            
            // Проверяем последний символ на знак препинания
            if (word.length() > 1 && isPunctuation(word.charAt(word.length() - 1))) {
                hasPunctuation = true;
                punctuation = word.charAt(word.length() - 1);
                word = word.substring(0, word.length() - 1);
            }
            
            // Преобразуем слово, если оно не пустое
            if (word.length() > 0) {
                String transformedWord = word.substring(1) + word.charAt(0) + "ауч";
                if (hasPunctuation) {
                    transformedWord += punctuation;
                }
                words[i] = transformedWord;
            }
            
            result.append(words[i]);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    private static boolean isPunctuation(char c) {
        return c == '.' || c == ',' || c == '!' || c == '?' || c == ';' || c == ':';
    }
}