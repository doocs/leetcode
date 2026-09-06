class Solution {
    public int countRotations(String s, int k) {
        int n = s.length();
        int score = 0;

        for (int i = 0; i < n - 1; i++) {
            score += s.charAt(i) == s.charAt(i + 1) ? 1 : 0;
        }

        int ans = score == k ? 1 : 0;

        for (int i = n; i < n * 2 - 1; i++) {
            score += s.charAt(i % n) == s.charAt((i - 1) % n) ? 1 : 0;
            score -= s.charAt(i % n) == s.charAt((i + 1) % n) ? 1 : 0;
            ans += score == k ? 1 : 0;
        }

        return ans;
    }
}