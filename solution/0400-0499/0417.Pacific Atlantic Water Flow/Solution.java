class Solution {
    private int[][] heights;
    private int m;
    private int n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        this.heights = heights;
        Deque<int[]> q1 = new LinkedList<>();
        Deque<int[]> q2 = new LinkedList<>();
        Set<Integer> vis1 = new HashSet<>();
        Set<Integer> vis2 = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    vis1.add(i * n + j);
                    q1.offer(new int[]{i, j});
                }
                if (i == m - 1 || j == n - 1) {
                    vis2.add(i * n + j);
                    q2.offer(new int[]{i, j});
                }
            }
        }
        bfs(q1, vis1);
        bfs(q2, vis2);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = i * n + j;
                if (vis1.contains(x) && vis2.contains(x)) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }

    private void bfs(Deque<int[]> q, Set<Integer> vis) {
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                for (int i = 0; i < 4; ++i) {
                    int x = p[0] + dirs[i];
                    int y = p[1] + dirs[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis.contains(x * n + y) && heights[x][y] >= heights[p[0]][p[1]]) {
                        vis.add(x * n + y);
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
    }
}