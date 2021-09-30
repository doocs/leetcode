class Solution {
public:
    vector<int> p;
    int dirs[4][2] = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    void solve(vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        p.resize(m * n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (board[i][j] == 'O')
                {
                    if (i == 0 || j == 0 || i == m - 1 || j == n - 1) p[find(i * n + j)] = find(m * n);
                    else
                    {
                        for (auto e : dirs)
                        {
                            if (board[i + e[0]][j + e[1]] == 'O') p[find(i * n + j)] = find((i + e[0]) * n + j + e[1]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < m; ++i)
        {
            for (int j = 0; j < n; ++j)
            {
                if (board[i][j] == 'O' && find(i * n + j) != find(m * n)) board[i][j] = 'X';
            }
        }
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};