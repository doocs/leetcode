class Solution {
    private static Map<Integer, String> map;

    static {
        map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        map.put(100, "Hundred");
        map.put(1000, "Thousand");
        map.put(1000000, "Million");
        map.put(1000000000, "Billion");
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1000000000; i >= 1000; i /= 1000) {
            if (num >= i) {
                sb.append(get3Digits(num / i)).append(' ').append(map.get(i));
                num %= i;
            }
        }
        if (num > 0) {
            sb.append(get3Digits(num));
        }
        return sb.substring(1);
    }

    private String get3Digits(int num) {
        StringBuilder sb = new StringBuilder();
        if (num >= 100) {
            sb.append(' ').append(map.get(num / 100)).append(' ').append(map.get(100));
            num %= 100;
        }
        if (num > 0) {
            if (num < 20 || num % 10 == 0) {
                sb.append(' ').append(map.get(num));
            } else {
                sb.append(' ').append(map.get(num / 10 * 10)).append(' ').append(map.get(num % 10));
            }
        }
        return sb.toString();
    }
}
