public class Solution {
    public IList<IList<int>> CombinationSum2(int[] candidates, int target) {
        Array.Sort(candidates);
        var ans = new List<IList<int>>();
        var t = new List<int>();
        dfs(candidates, 0, 0, target, t, ans);
        return ans;
    }

    private void dfs(int[] candidates, int i, int s, int target, IList<int> t, IList<IList<int>> ans) {
        if (s > target) {
            return;
        }
        if (s == target) {
            ans.Add(new List<int>(t));
            return;
        }
        for (int j = i; j < candidates.Length; ++j) {
            if (j > i && candidates[j] == candidates[j - 1]) {
                continue;
            }
            t.Add(candidates[j]);
            dfs(candidates, j + 1, s + candidates[j], target, t, ans);
            t.RemoveAt(t.Count - 1);
        }
    }
}