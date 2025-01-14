public class Solution {
    public int MinDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int tr = tree[0], tc = tree[1];
        int sr = squirrel[0], sc = squirrel[1];
        int s = 0;

        foreach (var e in nuts) {
            s += Math.Abs(e[0] - tr) + Math.Abs(e[1] - tc);
        }
        s <<= 1;

        int ans = int.MaxValue;
        foreach (var e in nuts) {
            int a = Math.Abs(e[0] - tr) + Math.Abs(e[1] - tc);
            int b = Math.Abs(e[0] - sr) + Math.Abs(e[1] - sc);
            ans = Math.Min(ans, s - a + b);
        }

        return ans;
    }
}
