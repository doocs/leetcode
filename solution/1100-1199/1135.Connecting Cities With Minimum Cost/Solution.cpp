class Solution {
public:
    vector<int> p;

    int minimumCost(int n, vector<vector<int>>& connections) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        sort(connections.begin(), connections.end(), [](auto& a, auto& b) { return a[2] < b[2]; });
        int ans = 0;
        for (auto& e : connections) {
            int x = e[0] - 1, y = e[1] - 1, cost = e[2];
            if (find(x) == find(y)) continue;
            p[find(x)] = find(y);
            ans += cost;
            if (--n == 1) return ans;
        }
        return -1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};