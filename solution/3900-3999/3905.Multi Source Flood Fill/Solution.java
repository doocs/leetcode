class Solution {
    public int[][] colorGrid(int n, int m, int[][] sources) {
        int[][] ans = new int[n][m];
        List<int[]> q = new ArrayList<>();
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int[] s : sources) {
            ans[s[0]][s[1]] = s[2];
            q.add(new int[] {s[0], s[1], s[2]});
        }
        while (!q.isEmpty()) {
            Map<Long, Integer> vis = new HashMap<>();
            for (int[] curr : q) {
                int r = curr[0], c = curr[1], color = curr[2];
                for (int i = 0; i < 4; i++) {
                    int x = r + dirs[i], y = c + dirs[i + 1];
                    if (x >= 0 && x < n && y >= 0 && y < m && ans[x][y] == 0) {
                        long key = (long) x * m + y;
                        vis.put(key, Math.max(vis.getOrDefault(key, 0), color));
                    }
                }
            }
            q.clear();
            for (Map.Entry<Long, Integer> entry : vis.entrySet()) {
                int x = (int) (entry.getKey() / m);
                int y = (int) (entry.getKey() % m);
                int color = entry.getValue();
                ans[x][y] = color;
                q.add(new int[] {x, y, color});
            }
        }
        return ans;
    }
}
