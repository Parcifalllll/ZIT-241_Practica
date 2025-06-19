import java.net.URL;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class CaesarCipherBreaker {
    
    // Частотный словарь русского языка (по умолчанию)
    private static final Map<Character, Double> RUSSIAN_FREQ = new HashMap<>() {{
        put('а', 0.0801); put('б', 0.0159); put('в', 0.0454); put('г', 0.0170);
        put('д', 0.0298); put('е', 0.0845); put('ё', 0.0004); put('ж', 0.0094);
        put('з', 0.0165); put('и', 0.0735); put('й', 0.0121); put('к', 0.0349);
        put('л', 0.0440); put('м', 0.0321); put('н', 0.0670); put('о', 0.1097);
        put('п', 0.0281); put('р', 0.0473); put('с', 0.0547); put('т', 0.0626);
        put('у', 0.0262); put('ф', 0.0026); put('х', 0.0097); put('ц', 0.0048);
        put('ч', 0.0144); put('ш', 0.0073); put('щ', 0.0036); put('ъ', 0.0004);
        put('ы', 0.0190); put('ь', 0.0174); put('э', 0.0032); put('ю', 0.0064);
        put('я', 0.0201);
    }};

    public static void main(String[] args) throws Exception {
        // 1. Загружаем текст для анализа
        String trainingText = loadTextFromFishText();
        
        // 2. Строим частотный словарь из текста
        Map<Character, Double> customFreq = buildFrequencyDictionary(trainingText);
        
        // 3. Получаем зашифрованный текст от пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите зашифрованный текст:");
        String encryptedText = scanner.nextLine();
        
        // 4. Определяем наиболее вероятный сдвиг
        int bestShift = findBestShift(encryptedText, customFreq);
        
        // 5. Расшифровываем текст
        String decryptedText = decryptCaesar(encryptedText, bestShift);
        
        System.out.println("\nНаиболее вероятный сдвиг: " + bestShift);
        System.out.println("Расшифрованный текст:\n" + decryptedText);
    }
    
    // Загрузка текста с fish-text.ru
    private static String loadTextFromFishText() throws Exception {
        URL url = new URL("https://fish-text.ru/get?type=paragraph&number=10");
        Scanner scanner = new Scanner(url.openStream());
        StringBuilder text = new StringBuilder();
        while (scanner.hasNext()) {
            text.append(scanner.nextLine());
        }
        return text.toString().toLowerCase();
    }
    
    // Построение частотного словаря
    private static Map<Character, Double> buildFrequencyDictionary(String text) {
        Map<Character, Integer> counts = new HashMap<>();
        int totalLetters = 0;
        
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c) && isRussianLetter(c)) {
                char lowerC = Character.toLowerCase(c);
                counts.put(lowerC, counts.getOrDefault(lowerC, 0) + 1);
                totalLetters++;
            }
        }
        
        Map<Character, Double> freq = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            freq.put(entry.getKey(), (double)entry.getValue() / totalLetters);
        }
        
        return freq;
    }
    
    // Проверка на русскую букву
    private static boolean isRussianLetter(char c) {
        return (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я') || c == 'ё' || c == 'Ё';
    }
    
    // Поиск наилучшего сдвига
    private static int findBestShift(String encryptedText, Map<Character, Double> freqDict) {
        int bestShift = 0;
        double bestScore = Double.NEGATIVE_INFINITY;
        
        for (int shift = 0; shift < 32; shift++) {
            String decrypted = decryptCaesar(encryptedText, shift);
            double score = calculateFrequencyScore(decrypted, freqDict);
            
            if (score > bestScore) {
                bestScore = score;
                bestShift = shift;
            }
        }
        
        return bestShift;
    }
    
    // Расчет "правильности" текста по частотам
    private static double calculateFrequencyScore(String text, Map<Character, Double> freqDict) {
        Map<Character, Integer> counts = new HashMap<>();
        int totalLetters = 0;
        
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c) && isRussianLetter(c)) {
                char lowerC = Character.toLowerCase(c);
                counts.put(lowerC, counts.getOrDefault(lowerC, 0) + 1);
                totalLetters++;
            }
        }
        
        if (totalLetters == 0) return Double.NEGATIVE_INFINITY;
        
        double score = 0;
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            double expectedFreq = freqDict.getOrDefault(entry.getKey(), 0.0);
            double actualFreq = (double)entry.getValue() / totalLetters;
            score += Math.log(actualFreq / expectedFreq);
        }
        
        return score;
    }
    
    // Дешифровка Цезаря с заданным сдвигом
    private static String decryptCaesar(String text, int shift) {
        StringBuilder result = new StringBuilder();
        
        for (char c : text.toCharArray()) {
            if (isRussianLetter(c)) {
                boolean isUpper = Character.isUpperCase(c);
                char lowerC = Character.toLowerCase(c);
                
                // Для русского алфавита (33 буквы с ё)
                int originalPos = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".indexOf(lowerC);
                if (originalPos == -1) {
                    result.append(c);
                    continue;
                }
                
                int newPos = (originalPos - shift) % 33;
                if (newPos < 0) newPos += 33;
                
                char newChar = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".charAt(newPos);
                result.append(isUpper ? Character.toUpperCase(newChar) : newChar);
            } else {
                result.append(c);
            }
        }
        
        return result.toString();
    }
}