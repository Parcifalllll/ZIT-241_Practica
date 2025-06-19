import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите строку: ");
        String text = scanner.nextLine();
        
        System.out.print("Введите сдвиг: ");
        int shift = scanner.nextInt();
        
        System.out.print("Направление (L - влево, R - вправо): ");
        char direction = scanner.next().toUpperCase().charAt(0);
        
        String result = caesarCipher(text, shift, direction);
        System.out.println("Результат: " + result);
    }
    
    public static String caesarCipher(String text, int shift, char direction) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                int originalAlphabetPosition = c - base;
                int newAlphabetPosition;
                
                if (direction == 'L') {
                    // Сдвиг влево (дешифровка)
                    newAlphabetPosition = (originalAlphabetPosition - shift) % 26;
                    if (newAlphabetPosition < 0) {
                        newAlphabetPosition += 26;
                    }
                } else {
                    // Сдвиг вправо (шифровка по умолчанию)
                    newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                }
                
                char newChar = (char) (base + newAlphabetPosition);
                result.append(newChar);
            } else {
                // Не буквы оставляем как есть
                result.append(c);
            }
        }
        
        return result.toString();
    }
}