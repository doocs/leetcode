class Solution {
public:
    vector<int> p;

    int countComponents(int n, vector<vector<int>> &edges) {
        p.resize(n);
        for (int i = 0; i < n; ++i)
        {
            p[i] = i;
        }
        for (auto e : edges)
        {
            int a = e[0], b = e[1];
            p[find(b)] = find(a);
        }
        int cnt = 0;
        for (int i = 0; i < n; ++i)
        {
            if (i == find(i))
                ++cnt;
        }
        return cnt;
    }

    int find(int x) {
        if (p[x] != x)
        {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};