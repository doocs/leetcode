public class Solution {
    public bool IsReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy)
            return t != 1;
        return Math.Max(Math.Abs(sx - fx), Math.Abs(sy - fy)) <= t;
    }
}
