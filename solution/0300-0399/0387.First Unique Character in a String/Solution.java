class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> chars = new HashMap<>(26);
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            chars.put(ch, chars.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (chars.get(ch) == 1) return i;
        }
        return -1;
    }
}