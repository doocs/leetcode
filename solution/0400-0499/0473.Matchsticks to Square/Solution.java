class Solution {
    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % 4 != 0) {
            return false;
        }
        Arrays.sort(nums);
        int[] lens = new int[4];
        return dfs(nums, nums.length - 1, lens, sum / 4);
    }

    private boolean dfs(int[] nums, int index, int[] lens, int len) {
        if (lens[0] == len && lens[1] == len && lens[2] == len) {
            return true;
        }
        for (int i = 0; i < 4; ++i) {
            if (lens[i] + nums[index] <= len) {
                lens[i] += nums[index];
                if (dfs(nums, index - 1, lens, len)) {
                    return true;
                }
                lens[i] -= nums[index];
            }
        }
        return false;
    }
}
