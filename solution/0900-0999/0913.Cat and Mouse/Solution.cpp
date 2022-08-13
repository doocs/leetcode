class Solution {
public:
    vector<vector<vector<int>>> memo;
    vector<vector<int>> graph;

    int catMouseGame(vector<vector<int>>& graph) {
        int n = graph.size();
        this->graph = graph;
        memo.resize(n, vector<vector<int>>(n, vector<int>(2 * n + 10, -1)));
        return dfs(1, 2, 0);
    }

    int dfs(int i, int j, int k) {
        if (memo[i][j][k] != -1) return memo[i][j][k];
        if (k >= 2 * graph.size())
            memo[i][j][k] = 0;
        else if (i == 0)
            memo[i][j][k] = 1;
        else if (i == j)
            memo[i][j][k] = 2;
        else if (k % 2) {
            bool tie = false, win = false;
            for (int next : graph[j]) {
                if (next == 0) continue;
                int x = dfs(i, next, k + 1);
                if (x == 2) {
                    win = true;
                    memo[i][j][k] = 2;
                    break;
                }
                if (x == 0) tie = true;
            }
            if (!win) memo[i][j][k] = tie ? 0 : 1;
        } else {
            bool tie = false, win = false;
            for (int next : graph[i]) {
                int x = dfs(next, j, k + 1);
                if (x == 1) {
                    win = true;
                    memo[i][j][k] = 1;
                    break;
                }
                if (x == 0) tie = true;
            }
            if (!win) memo[i][j][k] = tie ? 0 : 2;
        }
        return memo[i][j][k];
    }
};