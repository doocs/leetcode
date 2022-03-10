class Solution {
    private List<List<Integer>> ans;
    private int[] nums;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ans = new ArrayList<>();
        Arrays.sort(nums);
        this.nums = nums;
        dfs(0, new ArrayList<>());
        return ans;
    }

    private void dfs(int u, List<Integer> t) {
        ans.add(new ArrayList<>(t));
        for (int i = u; i < nums.length; ++i) {
            if (i != u && nums[i] == nums[i - 1]) {
                continue;
            }
            t.add(nums[i]);
            dfs(i + 1, t);
            t.remove(t.size() - 1);
        }
    }
}