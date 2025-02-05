public class Solution {
    public IList<IList<int>> SubsetsWithDup(int[] nums) {
        Array.Sort(nums);
        int n = nums.Length;
        IList<IList<int>> ans = new List<IList<int>>();
        for (int mask = 0; mask < 1 << n; ++mask) {
            IList<int> t = new List<int>();
            bool ok = true;
            for (int i = 0; i < n; ++i) {
                if ((mask >> i & 1) == 1) {
                    if (i > 0 && (mask >> (i - 1) & 1) == 0 && nums[i] == nums[i - 1]) {
                        ok = false;
                        break;
                    }
                    t.Add(nums[i]);
                }
            }
            if (ok) {
                ans.Add(t);
            }
        }
        return ans;
    }
}
