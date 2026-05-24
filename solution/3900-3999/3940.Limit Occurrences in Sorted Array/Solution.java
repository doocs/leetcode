class Solution {
    public int[] limitOccurrences(int[] nums, int k) {
        int n = nums.length;
        int cnt = 1, l = 1;

        for (int r = 1; r < n; r++) {
            if (nums[r] != nums[r - 1]) {
                cnt = 1;
            } else {
                cnt++;
            }

            if (cnt <= k) {
                nums[l] = nums[r];
                l++;
            }
        }

        return Arrays.copyOf(nums, l);
    }
}