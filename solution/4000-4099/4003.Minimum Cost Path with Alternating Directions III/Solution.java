import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public long minCost(int m, int n, int[][] penalty) {
        long[][][] dist = new long[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], Long.MAX_VALUE);
            }
        }
        dist[0][0][1] = 1;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        pq.offer(new long[] {1, 0, 0, 1});

        int[][] dirs = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int i = (int) cur[1];
            int j = (int) cur[2];
            int k = (int) cur[3];

            if (i == m - 1 && j == n - 1) {
                return d;
            }
            if (d > dist[i][j][k]) {
                continue;
            }

            int p = penalty[i][j];

            long nd = d + p;
            if (nd < dist[i][j][k ^ 1]) {
                dist[i][j][k ^ 1] = nd;
                pq.offer(new long[] {nd, i, j, k ^ 1});
            }

            for (int idx = 0; idx < 4; idx++) {
                int x = i + dirs[idx][0];
                int y = j + dirs[idx][1];
                if (0 <= x && x < m && 0 <= y && y < n) {
                    nd = d + (long) (x + 1) * (y + 1) + ((idx & 1) ^ k) * (long) p;
                    if (nd < dist[x][y][k ^ 1]) {
                        dist[x][y][k ^ 1] = nd;
                        pq.offer(new long[] {nd, x, y, k ^ 1});
                    }
                }
            }
        }

        return -1;
    }
}