import java.util.Arrays;
import java.util.Comparator;

public class FitnessSort {
    public static void main(String[] args) {
        String input = "56 65 74 100 99 68 86 180 90";
        String sorted = sortByDigitSum(input);
        System.out.println(sorted); // Выведет: 100 180 90 56 65 74 68 86 99
    }

    public static String sortByDigitSum(String weights) {
        // Разбиваем строку на массив чисел
        String[] numbers = weights.split(" ");
        
        // Сортируем массив по сумме цифр каждого числа
        Arrays.sort(numbers, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int sumA = digitSum(a);
                int sumB = digitSum(b);
                return Integer.compare(sumA, sumB);
            }
        });
        
        // Собираем отсортированный массив обратно в строку
        return String.join(" ", numbers);
    }
    
    // Метод для вычисления суммы цифр числа
    private static int digitSum(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        return sum;
    }
}