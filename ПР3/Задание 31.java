import java.util.ArrayList;
import java.util.List;

public class BestTravel {
    public static List<Integer> chooseBestSum(int maxDistance, int citiesCount, List<Integer> distances) {
        // Проверка на валидность входных данных
        if (citiesCount <= 0 || distances == null || distances.size() < citiesCount) {
            return new ArrayList<>();
        }

        List<Integer> bestCombination = new ArrayList<>();
        int[] bestSum = {0};
        
        findCombinations(distances, citiesCount, 0, new ArrayList<>(), maxDistance, bestSum, bestCombination);
        
        return bestCombination.isEmpty() ? new ArrayList<>() : bestCombination;
    }
    
    private static void findCombinations(List<Integer> distances, int citiesCount, int startIndex,
                                       List<Integer> currentCombination, int maxDistance, 
                                       int[] bestSum, List<Integer> bestCombination) {
        // Базовый случай: найдена комбинация нужного размера
        if (currentCombination.size() == citiesCount) {
            int currentSum = sum(currentCombination);
            
            if (currentSum <= maxDistance && currentSum > bestSum[0]) {
                bestSum[0] = currentSum;
                updateBestCombination(bestCombination, currentCombination);
            }
            return;
        }
        
        // Рекурсивный случай: перебираем все возможные комбинации
        for (int i = startIndex; i < distances.size(); i++) {
            currentCombination.add(distances.get(i));
            findCombinations(distances, citiesCount, i + 1, currentCombination, 
                           maxDistance, bestSum, bestCombination);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
    
    // Вспомогательный метод для вычисления суммы элементов списка
    private static int sum(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }
    
    // Вспомогательный метод для обновления лучшей комбинации
    private static void updateBestCombination(List<Integer> best, List<Integer> current) {
        best.clear();
        best.addAll(current);
    }
}