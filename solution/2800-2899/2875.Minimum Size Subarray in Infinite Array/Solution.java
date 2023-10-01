class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        
        int minLength = n * 2 + 1;
        int l = 0;
        int sum = 0;

        for (int r = 0; r < n * 2; r++) {
            int start = l % n;
            int end = r % n;
            sum += nums[end];

            while (sum > k && l <= r) {
                start = l % n;
                sum -= nums[start];
                l++;
            }

            if (sum == k) {
                minLength = Math.min(minLength, r - l + 1);
                start = l % n;
                sum -= nums[start];
                l++;
            }
        }
        
        return minLength == n * 2 + 1 ? -1 : minLength;
    }
    public int minSizeSubarray(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        
        for (int num : nums) {
            sum += num;
        }
        int k = target % sum;
        int ans = target / sum * n;
        if (k == 0) {
            return ans;
        }
        int res = shortestSubarray(nums, k);
        return res == -1 ? -1 : ans + res;
    }
}