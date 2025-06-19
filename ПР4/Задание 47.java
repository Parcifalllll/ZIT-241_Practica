import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SnailSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите N (размер матрицы NxN): ");
        int n = scanner.nextInt();
        
        // Генерируем случайную матрицу NxN
        int[][] matrix = generateRandomMatrix(n);
        System.out.println("Исходная матрица:");
        printMatrix(matrix);
        
        // Сортируем змейкой
        int[] snailSorted = snailSort(matrix);
        System.out.println("Результат сортировки змейкой:");
        System.out.println(Arrays.toString(snailSorted));
    }
    
    // Генерация случайной матрицы NxN
    private static int[][] generateRandomMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = ThreadLocalRandom.current().nextInt(1, 101); // числа от 1 до 100
            }
        }
        return matrix;
    }
    
    // Печать матрицы
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
    
    // Сортировка змейкой
    public static int[] snailSort(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        
        int n = matrix.length;
        int[] result = new int[n * n];
        int index = 0;
        
        int rowStart = 0, rowEnd = n - 1;
        int colStart = 0, colEnd = n - 1;
        
        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Движение вправо
            for (int i = colStart; i <= colEnd; i++) {
                result[index++] = matrix[rowStart][i];
            }
            rowStart++;
            
            // Движение вниз
            for (int i = rowStart; i <= rowEnd; i++) {
                result[index++] = matrix[i][colEnd];
            }
            colEnd--;
            
            if (rowStart <= rowEnd) {
                // Движение влево
                for (int i = colEnd; i >= colStart; i--) {
                    result[index++] = matrix[rowEnd][i];
                }
                rowEnd--;
            }
            
            if (colStart <= colEnd) {
                // Движение вверх
                for (int i = rowEnd; i >= rowStart; i--) {
                    result[index++] = matrix[i][colStart];
                }
                colStart++;
            }
        }
        
        return result;
    }
}