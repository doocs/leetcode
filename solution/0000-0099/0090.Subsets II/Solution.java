class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, res, path);
        return res;
    }

    private void dfs(int[] nums, int i, List<List<Integer>> res, List<Integer> path) {
        res.add(new ArrayList<>(path));
        for (int j = i; j < nums.length; ++j) {
            if (j != i && nums[j] == nums[j - 1]) {
                continue;
            }
            path.add(nums[j]);
            dfs(nums, i + 1, res, path);
            path.remove(path.size() - 1);
        }
    }
}