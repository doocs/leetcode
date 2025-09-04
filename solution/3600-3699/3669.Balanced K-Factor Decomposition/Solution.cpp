class Solution {
public:
    static const int MX = 100001;
    static vector<vector<int>> g;

    vector<int> ans;
    vector<int> path;
    int cur;

    vector<int> minDifference(int n, int k) {
        if (g.empty()) {
            g.resize(MX);
            for (int i = 1; i < MX; i++) {
                for (int j = i; j < MX; j += i) {
                    g[j].push_back(i);
                }
            }
        }

        cur = INT_MAX;
        ans.clear();
        path.assign(k, 0);

        dfs(k - 1, n, INT_MAX, 0);
        return ans;
    }

private:
    void dfs(int i, int x, int mi, int mx) {
        if (i == 0) {
            int d = max(mx, x) - min(mi, x);
            if (d < cur) {
                cur = d;
                path[i] = x;
                ans = path;
            }
            return;
        }
        for (int y : g[x]) {
            path[i] = y;
            dfs(i - 1, x / y, min(mi, y), max(mx, y));
        }
    }
};

vector<vector<int>> Solution::g;
