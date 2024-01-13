class Solution {
    public int numIdenticalPairs(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (int v : cnt) {
            ans += v * (v - 1) / 2;
        }
        return ans;
    }
}