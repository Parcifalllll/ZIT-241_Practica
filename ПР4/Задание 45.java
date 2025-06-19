import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;

public class ArabicToRoman {
    private static final LinkedHashMap<Integer, String> NUMERALS = new LinkedHashMap<>() {{
        put(1000, "M");
        put(900, "CM");
        put(500, "D");
        put(400, "CD");
        put(100, "C");
        put(90, "XC");
        put(50, "L");
        put(40, "XL");
        put(10, "X");
        put(9, "IX");
        put(5, "V");
        put(4, "IV");
        put(1, "I");
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество лет (1-9999): ");
        int years = scanner.nextInt();
        
        if (years < 1 || years >= 10000) {
            System.out.println("Число должно быть в диапазоне 1-9999");
            return;
        }
        
        String roman = convertToRoman(years);
        System.out.println("Римскими цифрами: " + roman);
    }
    
    public static String convertToRoman(int number) {
        StringBuilder result = new StringBuilder();
        
        for (Map.Entry<Integer, String> entry : NUMERALS.entrySet()) {
            int value = entry.getKey();
            String numeral = entry.getValue();
            
            while (number >= value) {
                result.append(numeral);
                number -= value;
            }
        }
        
        return result.toString();
    }
}