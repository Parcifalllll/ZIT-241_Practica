public class FuscSequence {
    // Итеративная версия функции fusc
    public static int fusc(int n) {
        if (n == 0) return 0;  // базовый случай 1
        if (n == 1) return 1;  // базовый случай 2
        
        int a = 0;  // соответствует fusc(0)
        int b = 1;   // соответствует fusc(1)
        
        while (n > 1) {
            if (n % 2 == 0) {
                // Случай для четных чисел: fusc(2n) = fusc(n)
                n /= 2;
            } else {
                // Случай для нечетных чисел: fusc(2n+1) = fusc(n) + fusc(n+1)
                // Разбиваем на две части и продолжаем вычисление
                int temp = a;
                a = b;
                b = temp + b;
                n = (n - 1) / 2;
            }
        }
        
        return a + b;
    }

    public static void main(String[] args) {
        // Тестирование функции
        System.out.println("fusc(0) = " + fusc(0));  // 0
        System.out.println("fusc(1) = " + fusc(1));  // 1
        System.out.println("fusc(2) = " + fusc(2));  // 1
        System.out.println("fusc(3) = " + fusc(3));  // 2
        System.out.println("fusc(4) = " + fusc(4));  // 1
        System.out.println("fusc(5) = " + fusc(5));  // 3
        System.out.println("fusc(21) = " + fusc(21)); // 8
    }
}