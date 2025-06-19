import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CodeBreakerGame {
    private static final int CODE_LENGTH = 4;
    private static final int MAX_ATTEMPTS = 20;
    private static final int MIN_NUMBER = 0;
    private static final int MAX_NUMBER = 9;

    public static void main(String[] args) {
        // Генерация случайного кода
        int[] secretCode = generateRandomCode();
        System.out.println("Программа загадала 4-значный код. Попробуйте угадать!");
        System.out.println("У вас есть " + MAX_ATTEMPTS + " попыток.");
        
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        boolean guessed = false;
        
        while (attempts < MAX_ATTEMPTS && !guessed) {
            System.out.print("Попытка #" + (attempts + 1) + ": Введите 4 числа через пробел: ");
            int[] guess = readGuess(scanner);
            
            if (guess.length != CODE_LENGTH) {
                System.out.println("Нужно ввести ровно 4 числа!");
                continue;
            }
            
            int matches = countMatches(secretCode, guess);
            System.out.println("Совпадений: " + matches);
            
            if (matches == CODE_LENGTH) {
                guessed = true;
                System.out.println("Поздравляем! Вы угадали код за " + (attempts + 1) + " попыток!");
            }
            
            attempts++;
        }
        
        if (!guessed) {
            System.out.println("Вы исчерпали все попытки. Загаданный код был: " + Arrays.toString(secretCode));
        }
    }
    
    // Генерация случайного кода
    private static int[] generateRandomCode() {
        Random random = new Random();
        int[] code = new int[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            code[i] = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        }
        return code;
    }
    
    // Чтение догадки игрока
    private static int[] readGuess(Scanner scanner) {
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        int[] guess = new int[parts.length];
        
        for (int i = 0; i < parts.length; i++) {
            try {
                guess[i] = Integer.parseInt(parts[i]);
            } catch (NumberFormatException e) {
                guess[i] = -1; // Некорректный ввод
            }
        }
        
        return guess;
    }
    
    // Подсчет совпадений
    private static int countMatches(int[] secret, int[] guess) {
        int matches = 0;
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (secret[i] == guess[i]) {
                matches++;
            }
        }
        return matches;
    }
}