class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        int p = 0, q = 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (q < len) {
            if (map.containsKey(chars[q])) {
                // 防止p指针回溯，导致计算到重复字符的长度
                p = Math.max(p, map.get(chars[q]) + 1);
            }
            map.put(chars[q], q);
            max = Math.max(max, q - p + 1);
            ++q;
        }

        return max;
    }
}