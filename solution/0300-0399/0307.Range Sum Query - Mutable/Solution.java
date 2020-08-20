public class NumArray {	int[] array, helper;

	public NumArray(int[] nums) {

		array = new int[nums.length];
		helper = new int[nums.length + 1];

		for (int i = 0; i < nums.length; i++) {
			array[i] = nums[i];
		}

		for (int i = 0; i < nums.length; i++) {
			add(i + 1, nums[i]);
		}
	}

	private void add(int pos, int value) {
		// TODO Auto-generated method stub
		while (pos < helper.length) {
			helper[pos] += value;
			pos += lowBit(pos);
		}
	}

	private int lowBit(int pos) {

		return pos & (-pos);
	}

	private int sum(int pos) {

		int rt = 0;

		while (pos > 0) {
			rt += helper[pos];
			pos -= lowBit(pos);
		}

		return rt;
	}

	void update(int i, int val) {
		int delta = val - array[i];
		array[i] = val;
		add(i + 1, delta);
	}

	public int sumRange(int i, int j) {

		return sum(j + 1) - sum(i);
	}}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);