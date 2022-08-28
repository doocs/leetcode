class Solution {
public:
    int k;

    vector<vector<int>> buildMatrix(int k, vector<vector<int>>& rowConditions, vector<vector<int>>& colConditions) {
        this->k = k;
        auto row = f(rowConditions);
        auto col = f(colConditions);
        if (row.empty() || col.empty()) return {};
        vector<vector<int>> ans(k, vector<int>(k));
        vector<int> m(k + 1);
        for (int i = 0; i < k; ++i) {
            m[col[i]] = i;
        }
        for (int i = 0; i < k; ++i) {
            ans[i][m[row[i]]] = row[i];
        }
        return ans;
    }

    vector<int> f(vector<vector<int>>& cond) {
        vector<vector<int>> g(k + 1);
        vector<int> indeg(k + 1);
        for (auto& e : cond) {
            int a = e[0], b = e[1];
            g[a].push_back(b);
            ++indeg[b];
        }
        queue<int> q;
        for (int i = 1; i < k + 1; ++i) {
            if (!indeg[i]) {
                q.push(i);
            }
        }
        vector<int> res;
        while (!q.empty()) {
            for (int n = q.size(); n; --n) {
                int i = q.front();
                res.push_back(i);
                q.pop();
                for (int j : g[i]) {
                    if (--indeg[j] == 0) {
                        q.push(j);
                    }
                }
            }
        }
        return res.size() == k ? res : vector<int>();
    }
};