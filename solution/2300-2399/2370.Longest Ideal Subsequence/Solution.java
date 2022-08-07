class Solution {
    public int longestIdealString(String s, int k) {
        int n = s.length();
        int ans = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        Map<Character, Integer> d = new HashMap<>(26);
        d.put(s.charAt(0), 0);
        for (int i = 1; i < n; ++i) {
            char a = s.charAt(i);
            for (char b = 'a'; b <= 'z'; ++b) {
                if (Math.abs(a - b) > k) {
                    continue;
                }
                if (d.containsKey(b)) {
                    dp[i] = Math.max(dp[i], dp[d.get(b)] + 1);
                }
            }
            d.put(a, i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}