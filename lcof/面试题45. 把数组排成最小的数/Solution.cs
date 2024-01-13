public class Solution {
    public string MinNumber(int[] nums) {
        List<string> ans = new List<string>();
        foreach (int x in nums) {
            ans.Add(x.ToString());
        }
        ans.Sort((a, b) => (a + b).CompareTo(b + a));
        return string.Join("", ans);
    }
}
