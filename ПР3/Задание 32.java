import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class FibonacciDigitAnalysis {
    
    public static List<int[]> analyzeFibonacciDigit(int i) {
        if (i < 10 || i > 100000) {
            throw new IllegalArgumentException("i должно быть в диапазоне 10 ≤ i ≤ 100000");
        }
        
        // 1. Вычисляем i-е число Фибоначчи
        String fibNumber = computeLargeFibonacci(i);
        
        // 2. Анализируем цифры в числе
        return findMostFrequentDigits(fibNumber);
    }
    
    private static String computeLargeFibonacci(int n) {
        if (n == 0) return "0";
        if (n <= 2) return "1";
        
        String a = "1";
        String b = "1";
        
        for (int i = 3; i <= n; i++) {
            String temp = addLargeNumbers(a, b);
            a = b;
            b = temp;
        }
        
        return b;
    }
    
    private static String addLargeNumbers(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        
        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = (i >= 0) ? num1.charAt(i--) - '0' : 0;
            int digit2 = (j >= 0) ? num2.charAt(j--) - '0' : 0;
            
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result.append(sum % 10);
        }
        
        return result.reverse().toString();
    }
    
    private static List<int[]> findMostFrequentDigits(String number) {
        int[] digitCounts = new int[10];
        
        // Подсчитываем количество каждой цифры
        for (char c : number.toCharArray()) {
            digitCounts[c - '0']++;
        }
        
        // Находим максимальное количество
        int maxCount = 0;
        for (int count : digitCounts) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        
        // Собираем все цифры с максимальным количеством
        List<Integer> maxDigits = new ArrayList<>();
        for (int digit = 0; digit < 10; digit++) {
            if (digitCounts[digit] == maxCount) {
                maxDigits.add(digit);
            }
        }
        
        // Сортируем по убыванию и берем первую (наибольшую)
        Collections.sort(maxDigits, Collections.reverseOrder());
        int mostFrequentDigit = maxDigits.get(0);
        
        // Возвращаем список с одним элементом (можно расширить для нескольких цифр с одинаковым count)
        List<int[]> result = new ArrayList<>();
        result.add(new int[]{maxCount, mostFrequentDigit});
        return result;
    }

    public static void main(String[] args) {
        int i = 10;
        List<int[]> result = analyzeFibonacciDigit(i);
        System.out.printf("f(%d) = %s  # вернет %s%n", 
            i, computeLargeFibonacci(i), Arrays.toString(result.get(0)));
    }
}