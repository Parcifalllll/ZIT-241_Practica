import java.util.Arrays;
import java.util.Scanner;

public class SmartMemoryCell {
    private static final int CAPACITY = 3;
    private static int[] memory = new int[CAPACITY];
    private static int size = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("Введите число (или 'q' для выхода): ");
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                processNumber(num);
                printMemory();
            } else if (scanner.next().equalsIgnoreCase("q")) {
                break;
            }
        }
    }

    private static void processNumber(int num) {
        if (size < CAPACITY) {
            // Если есть свободные ячейки
            memory[size++] = num;
        } else {
            // Если нет свободных ячеек - заменяем наименьшее
            int minIndex = findMinIndex();
            memory[minIndex] = num;
        }
    }

    private static int findMinIndex() {
        int minIndex = 0;
        for (int i = 1; i < memory.length; i++) {
            if (memory[i] < memory[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static void printMemory() {
        System.out.println("Текущее состояние памяти: " + Arrays.toString(memory));
    }
}