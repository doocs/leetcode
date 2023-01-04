public class Solution {
    public IList<string> SummaryRanges(int[] nums) {
        var ans = new List<string>();
        for (int i = 0, j = 0, n = nums.Length; i < n; i = j + 1) {
            j = i;
            while (j + 1 < n && nums[j + 1] == nums[j] + 1) {
                ++j;
            }
            ans.Add(f(nums, i, j));
        }
        return ans;
    }

    public string f(int[] nums, int i, int j) {
        return i == j ? nums[i].ToString() : string.Format("{0}->{1}", nums[i], nums[j]);
    }
}