class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        Map<Character, Integer> chars = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); ++j) {
            char c = s.charAt(j);
            if (chars.containsKey(c)) {
                i = Math.max(i, chars.get(c) + 1);
            }
            chars.put(c, j);
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}