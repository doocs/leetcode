class Solution {
public:
    int minimumMoves(vector<vector<int>>& grid) {
        queue<string> q;
        q.push(f(grid));
        unordered_set<string> vis;
        vis.insert(f(grid));
        vector<int> dirs = {-1, 0, 1, 0, -1};

        for (int ans = 0;; ++ans) {
            int sz = q.size();
            while (sz--) {
                string p = q.front();
                q.pop();
                if (p == "111111111") {
                    return ans;
                }
                vector<vector<int>> cur = g(p);

                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        if (cur[i][j] > 1) {
                            for (int d = 0; d < 4; ++d) {
                                int x = i + dirs[d];
                                int y = j + dirs[d + 1];
                                if (x >= 0 && x < 3 && y >= 0 && y < 3 && cur[x][y] < 2) {
                                    vector<vector<int>> nxt = cur;
                                    nxt[i][j]--;
                                    nxt[x][y]++;
                                    string s = f(nxt);
                                    if (!vis.count(s)) {
                                        vis.insert(s);
                                        q.push(s);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

private:
    string f(const vector<vector<int>>& grid) {
        string s;
        for (const auto& row : grid) {
            for (int x : row) {
                s += to_string(x);
            }
        }
        return s;
    }

    vector<vector<int>> g(const string& s) {
        vector<vector<int>> grid(3, vector<int>(3));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                grid[i][j] = s[i * 3 + j] - '0';
            }
        }
        return grid;
    }
};
