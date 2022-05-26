class NumArray {

	private int[] nums;
	private int[] sums;

	public NumArray(int[] tmp) {
		this.nums = Arrays.copyOf(tmp, tmp.length);
		sums = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			sums[i + 1] += nums[i] + sums[i];
		}
	}

	public int sumRange(int i, int j) {
		if (i < 0 || j > nums.length || i > j) {
			return 0;
		}
		return sums[j + 1] - sums[i];
	}
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */