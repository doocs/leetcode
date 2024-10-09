class Solution {
    public int minChanges(int[] nums, int k) {
        int[] d = new int[k + 2];
        int n = nums.length;
        for (int i = 0; i < n / 2; ++i) {
            int x = Math.min(nums[i], nums[n - i - 1]);
            int y = Math.max(nums[i], nums[n - i - 1]);
            d[0] += 1;
            d[y - x] -= 1;
            d[y - x + 1] += 1;
            d[Math.max(y, k - x) + 1] -= 1;
            d[Math.max(y, k - x) + 1] += 2;
        }
        int ans = n, s = 0;
        for (int x : d) {
            s += x;
            ans = Math.min(ans, s);
        }
        return ans;
    }
}