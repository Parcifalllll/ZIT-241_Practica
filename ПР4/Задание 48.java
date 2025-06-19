import java.util.TreeSet;
import java.util.Scanner;
import java.util.Set;

public class USequenceGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество элементов последовательности: ");
        int n = scanner.nextInt();
        
        Set<Integer> sequence = generateUSequence(n);
        System.out.println("Последовательность u без дубликатов:");
        System.out.println(sequence);
    }
    
    public static Set<Integer> generateUSequence(int n) {
        Set<Integer> u = new TreeSet<>();
        u.add(1); // u(0) = 1
        
        int count = 1;
        int current = 1;
        
        while (count < n) {
            int y = 2 * current + 1;
            int z = 3 * current + 1;
            
            if (u.add(y)) {
                count++;
            }
            if (count < n && u.add(z)) {
                count++;
            }
            
            // Получаем следующее число из последовательности для обработки
            current = ((TreeSet<Integer>)u).higher(current);
            if (current == 0) break; // если больше нет чисел
        }
        
        return u;
    }
}