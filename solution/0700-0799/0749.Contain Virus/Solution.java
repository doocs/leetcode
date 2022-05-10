class Solution {
    private static final int[] DIRS = {-1, 0, 1, 0, -1};
    private List<Integer> c = new ArrayList<>();
    private List<Set<Integer>> areas = new ArrayList<>();
    private List<Set<Integer>> boundaries = new ArrayList<>();
    private int[][] isInfected;
    private int m;
    private int n;

    public int containVirus(int[][] isInfected) {
        m = isInfected.length;
        n = isInfected[0].length;
        this.isInfected = isInfected;
        int ans = 0;
        while (true) {
            boolean[][] vis = new boolean[m][n];
            areas.clear();
            c.clear();
            boundaries.clear();
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (isInfected[i][j] == 1 && !vis[i][j]) {
                        areas.add(new HashSet<>());
                        boundaries.add(new HashSet<>());
                        c.add(0);
                        dfs(i, j, vis);
                    }
                }
            }
            if (boundaries.isEmpty()) {
                break;
            }
            int idx = max(boundaries);
            ans += c.get(idx);
            for (int t = 0; t < areas.size(); ++t) {
                if (t == idx) {
                    for (int v : areas.get(t)) {
                        int i = v / n, j = v % n;
                        isInfected[i][j] = -1;
                    }
                } else {
                    for (int v : areas.get(t)) {
                        int i = v / n, j = v % n;
                        for (int k = 0; k < 4; ++k) {
                            int x = i + DIRS[k], y = j + DIRS[k + 1];
                            if (x >= 0 && x < m && y >= 0 && y < n && isInfected[x][y] == 0) {
                                isInfected[x][y] = 1;
                            }
                        }
                    }
                }
            }

        }
        return ans;
    }

    private int max(List<Set<Integer>> boundaries) {
        int idx = 0;
        int mx = boundaries.get(0).size();
        for (int i = 1; i < boundaries.size(); ++i) {
            int t = boundaries.get(i).size();
            if (mx < t) {
                mx = t;
                idx = i;
            }
        }
        return idx;
    }

    private void dfs(int i, int j, boolean[][] vis) {
        vis[i][j] = true;
        int idx = areas.size() - 1;
        areas.get(idx).add(i * n + j);
        for (int k = 0; k < 4; ++k) {
            int x = i + DIRS[k], y = j + DIRS[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n) {
                if (isInfected[x][y] == 1 && !vis[x][y]) {
                    dfs(x, y, vis);
                } else if (isInfected[x][y] == 0) {
                    c.set(idx, c.get(idx) + 1);
                    boundaries.get(idx).add(x * n + y);
                }
            }
        }
    }
}