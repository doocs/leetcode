class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int n = nums.length;
        boolean[] used = new boolean[n];
        dfs(0, n, nums, used, path, res);
        return res;
    }

    private void dfs(int u, int n, int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (u == n) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                dfs(u + 1, n, nums, used, path, res);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}