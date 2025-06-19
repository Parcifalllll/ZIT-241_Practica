import java.util.Scanner;

public class StickGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество палочек (1 <= n <= 10^18): ");
        long n = scanner.nextLong();
        
        if (n < 1 || n > 1_000_000_000_000_000_000L) {
            System.out.println("Некорректный ввод!");
            return;
        }
        
        long tanyaSticks = calculateTanyaSticks(n);
        System.out.println("Количество палочек у Тани: " + tanyaSticks);
    }
    
    public static long calculateTanyaSticks(long n) {
        long tanyaCount = 0;
        boolean isTanyaTurn = true;
        
        while (n > 0) {
            long taken;
            if (n % 2 == 0) {
                // Четное количество - берем половину (максимум) или 1
                taken = (n / 2 > 1) ? n / 2 : 1;
            } else {
                // Нечетное количество - берем только 1
                taken = 1;
            }
            
            if (isTanyaTurn) {
                tanyaCount += taken;
            }
            
            n -= taken;
            isTanyaTurn = !isTanyaTurn;
        }
        
        return tanyaCount;
    }
}