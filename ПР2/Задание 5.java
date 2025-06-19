import java.util.Scanner;

public class NumberSeries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите начало, шаг и количество шагов (через пробел): ");
        int start = scanner.nextInt();
        int step = scanner.nextInt();
        int count = scanner.nextInt();
        
        System.out.print("Ряд чисел: ");
        
        // Генерируем и выводим числа ряда
        for (int i = 0; i < count; i++) {
            System.out.print(start + i * step);
            if (i < count - 1) {
                System.out.print(", ");
            }
        }
    }
}