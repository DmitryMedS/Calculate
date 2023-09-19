import java.util.*;

class Calculator {
    public static String calc(String input) {
        try {
            String[] tokens = input.split("");
            String num1Str = tokens[0];
            String operation = tokens[1];
            String num2Str = tokens[2];

            int num1, num2;
            boolean isRoman = false;


            if (isRomanNumber(num1Str) && isRomanNumber(num2Str)) {
                num1 = romanToArabic(num1Str);
                num2 = romanToArabic(num2Str);
                isRoman = true;
            } else {
                num1 = Integer.parseInt(num1Str);
                num2 = Integer.parseInt(num2Str);
            }

            int result = 0;
            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        return "Ошибка: деление на ноль!";
                    }
                    break;
                default:
                    return "Ошибка: недопустимая операция!";
            }


            if (isRoman) {
                return arabicToRoman(result);
            } else {
                return String.valueOf(result);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return "Ошибка: некорректные данные!";
        }
    }

    private static boolean isRomanNumber(String s) {
        return s.matches("^[IVXLC]+$");
    }

    private static int romanToArabic(String roman) {
        Map<Character, Integer> romanToArabicMap = new LinkedHashMap<>();
        romanToArabicMap.put('C', 100);
        romanToArabicMap.put('L', 50);
        romanToArabicMap.put('X', 10);
        romanToArabicMap.put('V', 5);
        romanToArabicMap.put('I', 1);

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);
            int currentValue = romanToArabicMap.get(currentChar);

            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            prevValue = currentValue;
        }

        return result;
    }


    private static String arabicToRoman(int num) {
        if (num < 1 || num >= 100) {
            throw new IllegalArgumentException("Число должно быть в диапазоне от 1 до 100.");
        }

        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return  tens[(num % 100) / 10] + ones[num % 10];
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите арифметическое выражение (или 'выход' для завершения): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("выход")) {
                System.out.println("Программа завершена.");
                break;
            }

            String result = Calculator.calc(input);
            System.out.println("Результат: " + result);
        }

        scanner.close();
    }
}