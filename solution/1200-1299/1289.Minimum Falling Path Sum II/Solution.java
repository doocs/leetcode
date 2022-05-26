class Solution {
    public void rotate(int[] nums, int k) {
        int[] res = new int[nums.length];
        int leftInit = 0;
        if (nums.length < k) {
            k = k % nums.length;
        }
        for (int i = nums.length - k; i < nums.length; i++) {
            res[leftInit] = nums[i];
            leftInit++;
        }
        int rightInit = 0;
        for (int i = k; i < nums.length; i++) {
            res[i] = nums[rightInit];
            rightInit++;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = res[i];
        }

    }
}