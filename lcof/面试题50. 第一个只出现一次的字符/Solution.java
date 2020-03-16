class Solution {
    public char firstUniqChar(String s) {
        if ("".equals(s)) {
            return ' ';
        }
        Map<Character, Integer> map = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            map.put(c, map.get(c) == null ? 1 : 1 + map.get(c));
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return ' ';
    }
}