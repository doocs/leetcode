class Solution {
public:
    vector<int> p;

    vector<bool> distanceLimitedPathsExist(int n, vector<vector<int>>& edgeList, vector<vector<int>>& queries) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        sort(edgeList.begin(), edgeList.end(), [](const auto& e1, const auto& e2) {
            return e1[2] < e2[2];
        });
        int m = queries.size();
        vector<int> indexes(m);
        for (int i = 0; i < m; ++i) indexes[i] = i;
        sort(indexes.begin(), indexes.end(), [&](int i, int j) {
            return queries[i][2] < queries[j][2];
        });

        vector<bool> ans(m, false);
        int i = 0;
        for (int j : indexes) {
            int pj = queries[j][0], qj = queries[j][1], limit = queries[j][2];
            while (i < edgeList.size() && edgeList[i][2] < limit) {
                int u = edgeList[i][0], v = edgeList[i][1];
                p[find(u)] = find(v);
                ++i;
            }
            ans[j] = find(pj) == find(qj);
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};