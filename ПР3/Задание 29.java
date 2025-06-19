import java.util.Scanner;

public class SquareDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число n (0 < n < 100000): ");
        int n = scanner.nextInt();
        
        if (n <= 0 || n >= 100000) {
            System.out.println("Число должно быть в диапазоне 0 < n < 100000");
            return;
        }
        
        // Проверяем, является ли n разницей последовательных квадратов
        if ((n - 1) % 2 != 0) {
            System.out.println(n + " не является разницей последовательных квадратов");
        } else {
            int a = (n - 1) / 2;
            int b = a + 1;
            System.out.printf("%d = %d² - %d² = %d - %d%n", n, b, a, b*b, a*a);
        }
    }
}