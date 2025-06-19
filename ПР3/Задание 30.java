import java.util.Scanner;

public class SpecialNumberCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите число: ");
        int number = scanner.nextInt();
        
        System.out.print("Введите начальную степень: ");
        int startPower = scanner.nextInt();
        
        if (isSpecialNumber(number, startPower)) {
            System.out.println("Условие выполняется");
        } else {
            System.out.println("Условие не выполняется");
        }
    }
    
    public static boolean isSpecialNumber(int number, int startPower) {
        int sum = 0;
        int temp = number;
        int power = startPower;
        
        // Разбиваем число на цифры и вычисляем сумму степеней
        while (temp > 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, power);
            power++;
            temp /= 10;
        }
        
        // Проверяем, делится ли сумма на исходное число
        if (sum % number == 0) {
            int multiplier = sum / number;
            System.out.printf("%d = %d * %d%n", sum, number, multiplier);
            return true;
        }
        
        return false;
    }
}