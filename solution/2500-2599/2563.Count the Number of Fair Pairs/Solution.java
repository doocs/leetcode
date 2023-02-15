class Solution {
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int j = search(nums, lower - nums[i], i + 1);
            int k = search(nums, upper - nums[i] + 1, i + 1);
            ans += k - j;
        }
        return ans;
    }

    private int search(int[] nums, int x, int left) {
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}