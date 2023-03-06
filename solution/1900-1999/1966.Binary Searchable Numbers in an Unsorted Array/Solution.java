class Solution {
    public int binarySearchableNumbers(int[] nums) {
        int n = nums.length;
        int[] ok = new int[n];
        Arrays.fill(ok, 1);
        int mx = -1000000, mi = 1000000;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < mx) {
                ok[i] = 0;
            }
            mx = Math.max(mx, nums[i]);
        }
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (nums[i] > mi) {
                ok[i] = 0;
            }
            mi = Math.min(mi, nums[i]);
            ans += ok[i];
        }
        return ans;
    }
}