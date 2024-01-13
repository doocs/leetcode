class Solution {
    public String nextPalindrome(String num) {
        char[] nums = num.toCharArray();
        if (!nextPermutation(nums)) {
            return "";
        }
        int n = nums.length;
        for (int i = 0; i < n / 2; ++i) {
            nums[n - 1 - i] = nums[i];
        }
        return String.valueOf(nums);
    }

    private boolean nextPermutation(char[] nums) {
        int n = nums.length / 2;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }
        if (i < 0) {
            return false;
        }
        int j = n - 1;
        while (j >= 0 && nums[i] >= nums[j]) {
            --j;
        }
        swap(nums, i++, j);
        for (j = n - 1; i < j; ++i, --j) {
            swap(nums, i, j);
        }
        return true;
    }

    private void swap(char[] nums, int i, int j) {
        char t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}