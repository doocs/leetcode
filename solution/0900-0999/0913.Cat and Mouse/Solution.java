class Solution {
    private int[][][] memo;
    private int[][] graph;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        this.graph = graph;
        memo = new int[n][n][2 * n + 10];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < 2 * n + 10; ++k) {
                    memo[i][j][k] = -1;
                }
            }
        }
        return dfs(1, 2, 0);
    }

    private int dfs(int i, int j, int k) {
        // mouse / cat / steps
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        if (k >= 2 * graph.length) {
            // tie
            memo[i][j][k] = 0;
        } else if (i == 0) {
            // mouse wins
            memo[i][j][k] = 1;
        } else if (i == j) {
            // cat wins
            memo[i][j][k] = 2;
        } else if (k % 2 == 1) {
            // cat's turn
            boolean tie = false;
            boolean win = false;
            for (int next : graph[j]) {
                if (next == 0) {
                    continue;
                }
                int x = dfs(i, next, k + 1);
                if (x == 2) {
                    win = true;
                    memo[i][j][k] = 2;
                    break;
                }
                if (x == 0) {
                    // continue to find if exists winning case
                    tie = true;
                }
            }
            if (!win) {
                memo[i][j][k] = tie ? 0 : 1;
            }
        } else {
            // mouse's turn
            boolean tie = false;
            boolean win = false;
            for (int next : graph[i]) {
                int x = dfs(next, j, k + 1);
                if (x == 1) {
                    win = true;
                    memo[i][j][k] = 1;
                    break;
                }
                if (x == 0) {
                    // continue to find if exists winning case
                    tie = true;
                }
            }
            if (!win) {
                memo[i][j][k] = tie ? 0 : 2;
            }
        }
        return memo[i][j][k];
    }
}