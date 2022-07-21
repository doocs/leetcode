public class Solution {
    public string MinNumber(int[] nums) {
        List<string> ans = new List<string>();
        foreach (int temp in nums) {
            ans.Add(temp.ToString());
        }
        ans.Sort((a, b) => (a + b).CompareTo(b + a));
        return string.Join("", ans);
    }
}