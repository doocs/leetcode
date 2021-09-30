class Solution {
public:
    vector<int> p;

    int minimumCost(int n, vector<vector<int>> &connections) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        auto cmp = [](auto &a, auto &b)
        {
            return a[2] < b[2];
        };
        sort(connections.begin(), connections.end(), cmp);
        int res = 0;
        for (auto e : connections)
        {
            if (unite(e[0], e[1]))
            {
                res += e[2];
                --n;
                if (n == 1) return res;
            }
        }
        return -1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    bool unite(int a, int b) {
        int pa = find(a - 1), pb = find(b - 1);
        if (pa == pb) return false;
        p[pa] = pb;
        return true;
    }
};