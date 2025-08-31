class Solution {
    static final int MX = 100_001;
    static List<Integer>[] g = new ArrayList[MX];

    static {
        for (int i = 0; i < MX; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 1; i < MX; i++) {
            for (int j = i; j < MX; j += i) {
                g[j].add(i);
            }
        }
    }

    private int cur;
    private int[] ans;
    private int[] path;

    public int[] minDifference(int n, int k) {
        cur = Integer.MAX_VALUE;
        ans = null;
        path = new int[k];
        dfs(k - 1, n, Integer.MAX_VALUE, 0);
        return ans;
    }

    private void dfs(int i, int x, int mi, int mx) {
        if (i == 0) {
            int d = Math.max(mx, x) - Math.min(mi, x);
            if (d < cur) {
                cur = d;
                path[i] = x;
                ans = path.clone();
            }
            return;
        }
        for (int y : g[x]) {
            path[i] = y;
            dfs(i - 1, x / y, Math.min(mi, y), Math.max(mx, y));
        }
    }
}
