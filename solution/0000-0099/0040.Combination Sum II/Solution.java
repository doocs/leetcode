class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;
    private int target;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.target = target;
        this.candidates = candidates;
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s > target) {
            return;
        }
        if (s == target) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int j = i; j < candidates.length; ++j) {
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            t.add(candidates[j]);
            dfs(j + 1, s + candidates[j]);
            t.remove(t.size() - 1);
        }
    }
}