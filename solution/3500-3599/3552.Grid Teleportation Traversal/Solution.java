class Solution {
    public int minMoves(String[] matrix) {
        int m = matrix.length, n = matrix[0].length();
        Map<Character, List<int[]>> g = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String row = matrix[i];
            for (int j = 0; j < n; j++) {
                char c = row.charAt(j);
                if (Character.isAlphabetic(c)) {
                    g.computeIfAbsent(c, k -> new ArrayList<>()).add(new int[] {i, j});
                }
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        int INF = Integer.MAX_VALUE / 2;
        int[][] dist = new int[m][n];
        for (int[] arr : dist) Arrays.fill(arr, INF);
        dist[0][0] = 0;
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0});
        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int i = cur[0], j = cur[1];
            int d = dist[i][j];
            if (i == m - 1 && j == n - 1) return d;
            char c = matrix[i].charAt(j);
            if (g.containsKey(c)) {
                for (int[] pos : g.get(c)) {
                    int x = pos[0], y = pos[1];
                    if (d < dist[x][y]) {
                        dist[x][y] = d;
                        q.addFirst(new int[] {x, y});
                    }
                }
                g.remove(c);
            }
            for (int idx = 0; idx < 4; idx++) {
                int a = dirs[idx], b = dirs[idx + 1];
                int x = i + a, y = j + b;
                if (0 <= x && x < m && 0 <= y && y < n && matrix[x].charAt(y) != '#'
                    && d + 1 < dist[x][y]) {
                    dist[x][y] = d + 1;
                    q.addLast(new int[] {x, y});
                }
            }
        }
        return -1;
    }
}
