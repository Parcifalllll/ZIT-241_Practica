import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class RomanToArabic {
    private static final Map<Character, Integer> ROMAN_NUMERALS = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество лет римскими цифрами: ");
        String roman = scanner.nextLine().toUpperCase();
        
        try {
            int arabic = convertToArabic(roman);
            if (arabic < 1 || arabic >= 10000) {
                System.out.println("Число должно быть в диапазоне I - MMMMMMMMMCMXCIX (1-9999)");
            } else {
                System.out.println("Арабскими цифрами: " + arabic);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    public static int convertToArabic(String roman) {
        if (roman == null || roman.isEmpty()) {
            throw new IllegalArgumentException("Пустая строка");
        }
        
        int result = 0;
        int prevValue = 0;
        
        for (int i = roman.length() - 1; i >= 0; i--) {
            char c = roman.charAt(i);
            if (!ROMAN_NUMERALS.containsKey(c)) {
                throw new IllegalArgumentException("Недопустимый символ: " + c);
            }
            
            int currentValue = ROMAN_NUMERALS.get(c);
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }
        
        // Проверка правильности записи римского числа
        if (!roman.equals(toRoman(result))) {
            throw new IllegalArgumentException("Некорректная запись римского числа");
        }
        
        return result;
    }
    
    // Вспомогательный метод для обратной конвертации (проверки)
    private static String toRoman(int number) {
        String[] thousands = {"", "M", "MM", "MMM", "MMMM", "MMMMM", 
                             "MMMMMM", "MMMMMMM", "MMMMMMMM", "MMMMMMMMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", 
                            "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", 
                        "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", 
                        "VI", "VII", "VIII", "IX"};
        
        return thousands[number / 1000] + 
               hundreds[(number % 1000) / 100] + 
               tens[(number % 100) / 10] + 
               ones[number % 10];
    }
}