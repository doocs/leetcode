class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int n = s.length();
        if (k > n || k > 26) {
            return 0;
        }
        int[] cnt = new int[128];
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            ++cnt[s.charAt(i)];
            while (cnt[s.charAt(i)] > 1 || i - j + 1 > k) {
                cnt[s.charAt(j++)]--;
            }
            ans += i - j + 1 == k ? 1 : 0;
        }
        return ans;
    }
}