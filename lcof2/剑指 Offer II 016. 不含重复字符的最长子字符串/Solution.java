class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int n = s.length(), ans = 0;
        int left = 0, right = 0;
        while (right < n) {
            char ch = s.charAt(right++);
            window.merge(ch, 1, Integer::sum);
            while (window.get(ch) > 1) {
                window.merge(s.charAt(left++), -1, Integer::sum);
            }
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
