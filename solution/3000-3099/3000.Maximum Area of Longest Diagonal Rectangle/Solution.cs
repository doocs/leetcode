public class Solution {
    public int AreaOfMaxDiagonal(int[][] dimensions) {
        int ans = 0, mx = 0;
        foreach (var d in dimensions) {
            int l = d[0], w = d[1];
            int t = l * l + w * w;
            if (mx < t) {
                mx = t;
                ans = l * w;
            } else if (mx == t) {
                ans = Math.Max(ans, l * w);
            }
        }
        return ans;
    }
}
