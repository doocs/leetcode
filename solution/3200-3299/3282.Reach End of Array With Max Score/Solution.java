class Solution {
    public long findMaximumScore(List<Integer> nums) {
        long ans = 0;
        int mx = 0;
        for (int i = 0; i + 1 < nums.size(); ++i) {
            mx = Math.max(mx, nums.get(i));
            ans += mx;
        }
        return ans;
    }
}
