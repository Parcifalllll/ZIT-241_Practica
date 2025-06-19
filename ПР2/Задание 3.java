import java.util.Scanner;

public class RemoveVowels {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();
        
        String result = input.replaceAll("[аеёиоуыэюяАЕЁИОУЫЭЮЯaeiouAEIOU]", "");
        
        System.out.println("Результат: " + result);
    }
}