public class Solution {
    public int MaxSum(int[] nums) {
        int mx = nums.Max();
        if (mx <= 0) {
            return mx;
        }

        HashSet<int> s = new HashSet<int>();
        int ans = 0;

        foreach (int x in nums) {
            if (x < 0 || s.Contains(x)) {
                continue;
            }
            ans += x;
            s.Add(x);
        }

        return ans;
    }
}