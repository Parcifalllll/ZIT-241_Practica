import java.util.Scanner;

public class CaseConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();
        
        int upperCount = 0;
        int lowerCount = 0;
        
        // Подсчитываем количество заглавных и строчных букв
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c)) {
                upperCount++;
            } else if (Character.isLowerCase(c)) {
                lowerCount++;
            }
        }
        
        // Преобразуем строку в зависимости от условий
        String result;
        if (upperCount > lowerCount) {
            result = input.toUpperCase();
        } else if (lowerCount > upperCount) {
            result = input.toLowerCase();
        } else {
            result = input.toLowerCase();  // при равенстве - в нижний регистр
        }
        
        System.out.println("Результат: " + result);
    }
}