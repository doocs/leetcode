class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int ans = 0, mx = 0;
        for (var d : dimensions) {
            int l = d[0], w = d[1];
            int t = l * l + w * w;
            if (mx < t) {
                mx = t;
                ans = l * w;
            } else if (mx == t) {
                ans = Math.max(ans, l * w);
            }
        }
        return ans;
    }
}