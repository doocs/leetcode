class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        for (char c : s.toCharArray()) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
        int cnt = 0;
        for (int v : counter.values()) {
            cnt += v % 2;
        }
        return cnt < 2;
    }
}