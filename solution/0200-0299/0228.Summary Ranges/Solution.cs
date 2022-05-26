public class Solution {
    public IList<string> SummaryRanges(int[] nums) {
        var res = new List<string>();
        for (int i = 0, j = 0, n = nums.Length; j < n;)
        {
            while (j + 1 < n && nums[j] + 1 == nums[j + 1])
            {
                ++j;
            }
            res.Add(make(nums, i, j));
            i = j + 1;
            j = i;
        }
        return res;
    }

    public string make(int[] nums, int i, int j) {
        return i == j ? nums[i].ToString() : string.Format("{0}->{1}", nums[i], nums[j]);
    }
}