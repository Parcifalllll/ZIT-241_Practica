import java.util.Scanner;

public class Greeting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        
        // Форматируем имя: первая буква заглавная, остальные строчные
        String formattedName = name.substring(0, 1).toUpperCase() 
                           + name.substring(1).toLowerCase();
        
        System.out.println("Привет, " + formattedName + "!");
    }
}