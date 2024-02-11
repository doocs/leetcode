class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length;
        int m = pattern.length;
        int count = 0;
        for (int i = 0; i <= n - m - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if ((pattern[j] == 1 && nums[i + j + 1] <= nums[i + j]) ||
                    (pattern[j] == 0 && nums[i + j + 1] != nums[i + j]) ||
                    (pattern[j] == -1 && nums[i + j + 1] >= nums[i + j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        return count;
    }
}
