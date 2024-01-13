class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0, n = nums.length; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int left = j + 1, right = n;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (nums[mid] >= nums[i] + nums[j]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                ans += left - j - 1;
            }
        }
        return ans;
    }
}