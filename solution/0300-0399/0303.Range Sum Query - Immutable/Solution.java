class NumArray {
    private int[] s;

    public NumArray(int[] nums) {
        int n = nums.length;
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return s[right + 1] - s[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */