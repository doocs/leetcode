class Solution {
public:
    vector<int> p;

    vector<bool> friendRequests(int n, vector<vector<int>>& restrictions, vector<vector<int>>& requests) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        vector<bool> ans;
        for (auto& req : requests) {
            int u = req[0], v = req[1];
            if (find(u) == find(v))
                ans.push_back(true);
            else {
                bool valid = true;
                for (auto& res : restrictions) {
                    int x = res[0], y = res[1];
                    if ((find(u) == find(x) && find(v) == find(y)) || (find(u) == find(y) && find(v) == find(x))) {
                        valid = false;
                        break;
                    }
                }
                ans.push_back(valid);
                if (valid) p[find(u)] = find(v);
            }
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};