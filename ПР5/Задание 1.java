import java.io.*;
import java.util.*;
import java.util.regex.*;

public class AdvancedCalculator {
    private static final String HISTORY_FILE = "calculator_history.txt";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Вычислить выражение");
            System.out.println("2. Показать историю вычислений");
            System.out.println("3. Выход");
            System.out.print("> ");
            
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число от 1 до 3");
                scanner.nextLine(); // очистка буфера
                continue;
            }
            scanner.nextLine(); // очистка буфера
            
            switch (choice) {
                case 1:
                    calculateExpression(scanner);
                    break;
                case 2:
                    showHistory();
                    break;
                case 3:
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
    
    private static void calculateExpression(Scanner scanner) {
        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();
        
        try {
            // Обработка модулей |x|
            expression = processAbsoluteValues(expression);
            
            // Вычисление выражения
            double result = evaluateExpression(expression);
            
            // Форматирование результата
            String formattedResult;
            if (result == (long) result) {
                formattedResult = String.format("%d", (long) result);
            } else {
                formattedResult = String.format("%.4f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
            }
            
            // Сохранение в историю
            String historyEntry = expression + " = " + formattedResult;
            saveToHistory(historyEntry);
            
            System.out.println("Результат: " + formattedResult);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private static String processAbsoluteValues(String expr) {
        Pattern pattern = Pattern.compile("\\|([^|]+)\\|");
        Matcher matcher = pattern.matcher(expr);
        
        while (matcher.find()) {
            String inside = matcher.group(1);
            double value = evaluateExpression(inside);
            expr = expr.replace(matcher.group(), String.valueOf(Math.abs(value)));
        }
        
        return expr;
    }
    
    private static double evaluateExpression(String expr) {
        // Удаляем все пробелы
        expr = expr.replaceAll("\\s+", "");
        
        // Используем стековый алгоритм для вычисления
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            
            if (Character.isDigit(c) || c == '.') {
                // Собираем число
                StringBuilder numStr = new StringBuilder();
                while (i < expr.length() && 
                      (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    numStr.append(expr.charAt(i++));
                }
                i--;
                
                numbers.push(Double.parseDouble(numStr.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.pop();
            } else if (isOperator(c)) {
                while (!operators.empty() && hasPrecedence(c, operators.peek())) {
                    numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(c);
            }
        }
        
        while (!operators.empty()) {
            numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
        }
        
        return numbers.pop();
    }
    
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^' || c == '\\';
    }
    
    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        
        // Приоритеты операторов
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('^', 4);
        precedence.put('*', 3);
        precedence.put('/', 3);
        precedence.put('%', 3);
        precedence.put('\\', 3);
        precedence.put('+', 2);
        precedence.put('-', 2);
        
        return precedence.get(op1) <= precedence.get(op2);
    }
    
    private static double applyOperator(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': 
                if (b == 0) throw new ArithmeticException("Деление на ноль");
                return a / b;
            case '%': return a % b;
            case '\\': return Math.floor(a / b); // Целочисленное деление
            case '^': return Math.pow(a, b);
            default: throw new IllegalArgumentException("Неизвестный оператор: " + op);
        }
    }
    
    private static void saveToHistory(String entry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE, true))) {
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении в историю: " + e.getMessage());
        }
    }
    
    private static void showHistory() {
        System.out.println("\nИстория вычислений:");
        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("История вычислений пуста.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении истории: " + e.getMessage());
        }
    }
}