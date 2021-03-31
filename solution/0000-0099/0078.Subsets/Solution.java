class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, res, path);
        return res;
    }

    private void dfs(int[] nums, int i, List<List<Integer>> res, List<Integer> path) {
        res.add(new ArrayList<>(path));
        while (i < nums.length) {
            path.add(nums[i]);
            dfs(nums, i + 1, res, path);
            path.remove(path.size() - 1);
            ++i;
        }
    }
}