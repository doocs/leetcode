public class Solution {
    public bool FindSafeWalk(IList<IList<int>> grid, int health) {
        int m = grid.Count;
        int n = grid[0].Count;
        int inf = int.MaxValue / 2;

        int[,] dist = new int[m, n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dist[i, j] = inf;
            }
        }

        dist[0, 0] = grid[0][0];

        var q = new Queue<(int x, int y)>();
        q.Enqueue((0, 0));

        int[] dirs = new int[] { -1, 0, 1, 0, -1 };

        while (q.Count > 0) {
            var (x, y) = q.Dequeue();

            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i];
                int ny = y + dirs[i + 1];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int newCost = dist[x, y] + grid[nx][ny];
                    if (newCost < dist[nx, ny]) {
                        dist[nx, ny] = newCost;
                        q.Enqueue((nx, ny));
                    }
                }
            }
        }

        return dist[m - 1, n - 1] < health;
    }
}