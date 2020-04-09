class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] dp = new int[n];
        int res = 1;
        Map<Character, Integer> map = new HashMap<>();
        dp[0] = 1;
        map.put(chars[0], 0);
        for (int i = 1; i < n; ++i) {
            if (chars[i] == chars[i - 1]) {
                dp[i] = 1;
            } else {
                if (map.get(chars[i]) == null) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = Math.min(dp[i - 1] + 1, i - map.get(chars[i]));
                }
            }
            map.put(chars[i], i);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}