class Solution {
    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<String, Integer>(13) {
            {
                put("I", 1);
                put("V", 5);
                put("X", 10);
                put("L", 50);
                put("C", 100);
                put("D", 500);
                put("M", 1000);
                put("IV", 4);
                put("IX", 9);
                put("XL", 40);
                put("XC", 90);
                put("CD", 400);
                put("CM", 900);
            }
        };

        int res = 0;
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            if (i != len - 1 && map.get(s.substring(i, i + 2)) != null) {
                res += map.get(s.substring(i, i + 2));
                ++i;
                continue;
            }
            res += map.get(s.substring(i, i + 1));
        }
        return res;

    }
}