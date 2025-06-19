import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JosephusProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите количество игроков: ");
        int playersCount = scanner.nextInt();
        
        System.out.print("Введите номер начального игрока: ");
        int startPlayer = scanner.nextInt();
        
        List<Integer> eliminationOrder = playJosephusGame(playersCount, startPlayer);
        
        System.out.println("Порядок выбывания: " + eliminationOrder);
        System.out.println("Победитель: " + eliminationOrder.get(eliminationOrder.size() - 1));
    }
    
    public static List<Integer> playJosephusGame(int playersCount, int startPlayer) {
        List<Integer> players = new ArrayList<>();
        List<Integer> eliminated = new ArrayList<>();
        
        // Инициализация списка игроков
        for (int i = 1; i <= playersCount; i++) {
            players.add(i);
        }
        
        int currentIndex = (startPlayer - 1) % players.size(); // Начинаем с указанного игрока
        
        while (players.size() > 1) {
            // Вычисляем индекс игрока, который выбывает (каждый 3-й)
            currentIndex = (currentIndex + 2) % players.size();
            
            // Добавляем выбывшего игрока в результат
            eliminated.add(players.remove(currentIndex));
        }
        
        // Добавляем победителя в конец списка
        eliminated.add(players.get(0));
        
        return eliminated;
    }
}