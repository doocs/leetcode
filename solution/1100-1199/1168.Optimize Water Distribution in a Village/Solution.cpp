class Solution {
public:
    vector<int> p;

    int minCostToSupplyWater(int n, vector<int>& wells, vector<vector<int>>& pipes) {
        p.resize(n + 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (int i = 0; i < n; ++i) pipes.push_back({0, i + 1, wells[i]});
        sort(pipes.begin(), pipes.end(), [](const auto& a, const auto& b) {
            return a[2] < b[2];
        });
        int res = 0;
        for (auto e : pipes) {
            if (find(e[0]) == find(e[1])) continue;
            p[find(e[0])] = find(e[1]);
            res += e[2];
            --n;
            if (n == 0) break;
        }
        return res;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};