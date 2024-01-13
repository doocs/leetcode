class Solution {
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (n > m) {
            return "";
        }
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char ch : t.toCharArray()) {
            need.merge(ch, 1, Integer::sum);
        }
        int start = 0, minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;
        while (right < m) {
            window.merge(s.charAt(right++), 1, Integer::sum);
            while (check(need, window)) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                window.merge(s.charAt(left++), -1, Integer::sum);
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    private boolean check(Map<Character, Integer> need, Map<Character, Integer> window) {
        for (Map.Entry<Character, Integer> entry : need.entrySet()) {
            if (window.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}