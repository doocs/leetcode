public class Solution {
    public bool IsStraight(int[] nums) {
        bool[] vis = new bool[14];
        int mi = 20, mx = -1;
        foreach(int x in nums) {
            if (x == 0) {
                continue;
            }
            if (vis[x]) {
                return false;
            }
            vis[x] = true;
            mi = Math.Min(mi, x);
            mx = Math.Max(mx, x);
        }
        return mx - mi <= 4;
    }
}
