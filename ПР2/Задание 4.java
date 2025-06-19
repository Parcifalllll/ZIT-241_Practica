import java.util.Scanner;

public class SquareSeries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите первое число: ");
        int num1 = scanner.nextInt();
        
        System.out.print("Введите второе число: ");
        int num2 = scanner.nextInt();
        
        // Определяем начало (меньшее число) и шаг
        int start = Math.min(num1, num2);
        int step = start;
        int end = Math.max(num1, num2);
        
        System.out.print("Квадраты чисел: ");
        
        // Генерируем числа от start до end с шагом step и выводим их квадраты
        for (int i = start; i <= end; i += step) {
            System.out.print(i * i);
            if (i + step <= end) {
                System.out.print(", ");
            }
        }
    }
}