class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0, mx = -1;
        for (int x : cnt) {
            if (mx < x) {
                mx = x;
                ans = x;
            } else if (mx == x) {
                ans += x;
            }
        }
        return ans;
    }
}