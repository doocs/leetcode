public class Solution {
	public static int rob(int[] nums) {

		if (nums == null || nums.length == 0) {
			return 0;
		}

		if (nums.length == 1) {
			return nums[0];
		}

		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}

		return Math.max(help(Arrays.copyOfRange(nums, 0, nums.length - 1)),
				help(Arrays.copyOfRange(nums, 1, nums.length)));
	}

	public static int help(int[] nums) {

		if (nums.length == 0) {
			return 0;
		}

		if (nums.length == 1) {
			return nums[0];
		}

		int[] P = new int[nums.length];

		P[0] = nums[0];
		P[1] = Math.max(nums[0], nums[1]);

		for (int i = 2; i < nums.length; i++) {
			P[i] = Math.max(nums[i] + P[i - 2], P[i - 1]);
		}

		return P[nums.length - 1];
	}
}