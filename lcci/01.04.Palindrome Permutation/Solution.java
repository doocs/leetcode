class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0, n = s.length(); i < n; ++i) {
            char c = s.charAt(i);
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        int cnt = 0;
        for (int val : counter.values()) {
            if ((val & 1) == 1) {
                ++cnt;
            }
            if (cnt > 1) {
                return false;
            }
        }
        return true;
    }
}