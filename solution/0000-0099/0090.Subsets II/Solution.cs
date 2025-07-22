public class Solution {
    private IList<IList<int>> ans = new List<IList<int>>();
    private IList<int> t = new List<int>();
    private int[] nums;

    public IList<IList<int>> SubsetsWithDup(int[] nums) {
        Array.Sort(nums);
        this.nums = nums;
        Dfs(0);
        return ans;
    }

    private void Dfs(int i) {
        if (i >= nums.Length) {
            ans.Add(new List<int>(t));
            return;
        }
        t.Add(nums[i]);
        Dfs(i + 1);
        t.RemoveAt(t.Count - 1);
        while (i + 1 < nums.Length && nums[i + 1] == nums[i]) {
            ++i;
        }
        Dfs(i + 1);
    }
}
