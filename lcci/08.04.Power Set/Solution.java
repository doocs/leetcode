class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        dfs(0, new ArrayList<>());
        return ans;
    }

    private void dfs(int u, List<Integer> t) {
        if (u == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        dfs(u + 1, t);
        t.add(nums[u]);
        dfs(u + 1, t);
        t.remove(t.size() - 1);
    }
}