class Solution {
    public int minimizeTheDifference(int[][] mat, int target) {
        Set<Integer> f = new HashSet<>();
        f.add(0);
        for (var row : mat) {
            Set<Integer> g = new HashSet<>();
            for (int a : f) {
                for (int b : row) {
                    g.add(a + b);
                }
            }
            f = g;
        }
        int ans = 1 << 30;
        for (int v : f) {
            ans = Math.min(ans, Math.abs(v - target));
        }
        return ans;
    }
}