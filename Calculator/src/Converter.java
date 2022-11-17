import java.util.TreeMap;

class Converter {
    //Создаем 2 дерева
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>();
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>();


    //Карта значений
    Converter() throws Exception {
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);


        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");


    }


    //Метод, который указывает нам на Ключ
    boolean isRoman(String number) {
        return romanKeyMap.containsKey(number.charAt(0));

    }


    //Перевод Int в римскую систему
    String intToRoman(int number) throws Exception { //46
        String roman = "";
        int arabianKey;
        do {
            if (number < 1) throw new Exception("Римской системе нет отрицательных чисел!");
            arabianKey = arabianKeyMap.floorKey(number);  //40 + 5 + 1
            roman += arabianKeyMap.get(arabianKey);      //XL V I
            number -= arabianKey;
        } while (number != 0);
        return roman;
    }


    //Перевод в арабскую систему
    int romanToInt(String s) throws Exception {
        int end = s.length() - 1;
        char[] arr = s.toCharArray();
        int arabian;
        int result = romanKeyMap.get(arr[end]);
        for (int i = end - 1; i >= 0; i--) {
            arabian = romanKeyMap.get(arr[i]);

            if (arabian < romanKeyMap.get(arr[i + 1])) {
                result -= arabian;
            } else {
                result += arabian;
            }
        }
        return result;

    }
}