public class Solution {
    public int[][] FindContinuousSequence(int target) {
        List<int[]> ans = new List<int[]>();
        int l = 1, r = 2;
        while (l < r) {
            int s = (l + r) * (r - l + 1) >> 1;
            if (s == target) {
                List<int> t = new List<int>();
                for (int i = l; i <= r; i++) {
                    t.Add(i);
                }
                l += 1;
                ans.Add(t.ToArray());
            } else if (s < target) {
                r += 1;
            } else {
                l += 1;
            }
        }
        return ans.ToArray();
    }
}
