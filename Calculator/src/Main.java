import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        //Так же создаем сканнер, чтобы он получал выражение из консоли
        Scanner scan = new Scanner(System.in);

        //Просим пользователя ввести выражение и определяем его
        System.out.println("""
                Введите арифметическое выражение:\s
                Можно писать арабские и римские цифры от1 до 10 включительно;
                Используйте только один знак +, -, * или /, чтобы посчитать.
                Начните ввод в строке ниже:
                """);
        String usersCount = scan.nextLine();

        calc(usersCount);
    }


    public static String calc(String input) throws Exception {


        //Создаем конвертер для проверки системы чисел
        Converter converter = new Converter();
        String[] actForUser = {"+", "-", "/", "*"};
        String[] actArray = {"\\+", "-", "/", "\\*"};


        //Проверяем кол-во
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '+', '-', '*', '/' -> count++;
            }
        }

        //Проверяем на исключения: какое у нас кол-во операторов (математических знаков +, - и пр.)
        if (count == 0) throw new Exception("Не является математической операцией!"); //Нет никакого знака
        if (count != 1)
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)!"); //Больше 1 знака


        //Определяем оператор:
        int Index = -1;
        for (int i = 0; i < actForUser.length; i++) {
            if (input.contains(actForUser[i])) {
                Index = i;
                break;
            }
        }


        //Делим введенную пользователем строку на массив по оператору (математическому знаку)
        String[] data = input.split(actArray[Index]);


        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;


            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);


            //если римские, то конвертируем их в арабские
            if (isRoman) {
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);


            //если арабские, конвертируем их из строки в число
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }


            //По условию задачи принимаются числа [1-10]
            if ((a >= 1 && a <= 10) && (b >= 1 && b <= 10)) {


                //Выполняем с числами действие указанное пользователем
                int result = switch (actForUser[Index]) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    default -> a / b;
                };


                //Если числа были римские - возвращаем результат римскими цифрами
                if (isRoman) {
                    System.out.println(converter.intToRoman(result));



                //Если числа были арабские - результат будет написан арабскими цифрами
                } else {
                    System.out.println(result);
                }

            //Исключение, если числа выходят за диапозон [1-10]
            } else {
                throw new Exception("Числа должны быть от 1 до 10 включительно!");
            }
        }
        //Исключение, если используются разныве системы счисления
        throw new Exception("Используются разные системы счисления!");
    }
}
