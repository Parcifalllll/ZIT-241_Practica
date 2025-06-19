import java.util.Scanner;

public class StickGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество палочек (1 <= n <= 10^18): ");
        long n = scanner.nextLong();
        
        if (n < 1 || n > 1e18) {
            System.out.println("Некорректный ввод!");
            return;
        }
        
        String winner = determineWinner(n);
        System.out.println("Победитель: " + winner);
    }
    
    public static String determineWinner(long n) {
        // Базовые случаи
        if (n == 1) return "Таня"; // Проигрышная позиция
        if (n == 2) return "Саша"; // Можно взять половину (1) и выиграть
        
        // Анализируем оптимальную стратегию
        boolean isWinningPosition = false;
        while (n > 2) {
            if (n % 2 == 0) {
                // Для четных чисел проверяем, является ли n/2 проигрышной позицией
                if ((n / 2) % 2 != 0) {
                    // Если n/2 нечетное - это проигрышная позиция
                    isWinningPosition = true;
                    break;
                }
                // Продолжаем анализировать для n/2
                n /= 2;
            } else {
                // Для нечетных берем 1 палочку
                n -= 1;
            }
        }
        
        // Последний ход (n=2) всегда выигрышный
        return isWinningPosition || n == 2 ? "Саша" : "Таня";
    }
}