class Solution {
    public int conveyorBelt(String[] matrix, int[] start, int[] end) {
        int[] dirs = {-1, 0, 1, 0, -1};
        Map<Character, Integer> d = new HashMap<>(4);
        d.put('^', 0);
        d.put('>', 1);
        d.put('v', 2);
        d.put('<', 3);
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {start[0], start[1]});
        int m = matrix.length, n = matrix[0].length();
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, 1 << 30);
        }
        dist[start[0]][start[1]] = 0;
        while (true) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            if (i == end[0] && j == end[1]) {
                return dist[i][j];
            }
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                int t = dist[i][j] + (k == d.get(matrix[i].charAt(j)) ? 0 : 1);
                if (x >= 0 && x < m && y >= 0 && y < n && t < dist[x][y]) {
                    dist[x][y] = t;
                    if (dist[x][y] == dist[i][j]) {
                        q.offerFirst(new int[] {x, y});
                    } else {
                        q.offerLast(new int[] {x, y});
                    }
                }
            }
        }
    }
}