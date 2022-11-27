class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + (customers.charAt(i) == 'Y' ? 1 : 0);
        }
        int ans = 0, cost = 1 << 30;
        for (int j = 0; j <= n; ++j) {
            int t = j - s[j] + s[n] - s[j];
            if (cost > t) {
                ans = j;
                cost = t;
            }
        }
        return ans;
    }
}