class Solution {
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = (int) Arrays.stream(nums).sum();
		if (sum % k != 0) {
			return false;
		}

		Arrays.sort(nums);
		int low = 0, high = nums.length - 1;
		while (low < high) {
			int temp = nums[low];
			nums[low] = nums[high];
			nums[high] = temp;
			low++;
			high--;
		}
		return dfs(nums, new int[k], 0, sum / k);
	}

	private boolean dfs(int[] nums, int[] cur, int i, int target) {
		if (i == nums.length) {
			return true;
		}
		for (int j = 0; j < cur.length; j++) {
            if (j > 0 && cur[j - 1] == cur[j]) {
                continue;
            }
			cur[j] += nums[i];
			if (cur[j] <= target && dfs(nums, cur, i + 1, target)) {
				return true;
			}
			cur[j] -= nums[i];
		}
		return false;
	}
}
