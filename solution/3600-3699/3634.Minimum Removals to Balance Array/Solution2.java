class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = n;
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r < n && nums[r] <= (long) nums[l] * k) {
                r++;
            }
            ans = Math.min(ans, n - (r - l));
        }
        return ans;
    }
}
