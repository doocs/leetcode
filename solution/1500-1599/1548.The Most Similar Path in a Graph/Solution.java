class Solution {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] r : roads) {
            int a = r[0], b = r[1];
            g[a].add(b);
            g[b].add(a);
        }
        int m = targetPath.length;
        final int inf = 1 << 30;
        int[][] f = new int[m][n];
        int[][] pre = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(f[i], inf);
            Arrays.fill(pre[i], -1);
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = targetPath[0].equals(names[j]) ? 0 : 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k : g[j]) {
                    int t = f[i - 1][k] + (targetPath[i].equals(names[j]) ? 0 : 1);
                    if (t < f[i][j]) {
                        f[i][j] = t;
                        pre[i][j] = k;
                    }
                }
            }
        }
        int mi = inf, k = 0;
        for (int j = 0; j < n; ++j) {
            if (f[m - 1][j] < mi) {
                mi = f[m - 1][j];
                k = j;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = m - 1; i >= 0; --i) {
            ans.add(k);
            k = pre[i][k];
        }
        Collections.reverse(ans);
        return ans;
    }
}