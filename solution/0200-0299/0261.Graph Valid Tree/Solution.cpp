class Solution {
public:
    vector<int> p;

    bool validTree(int n, vector<vector<int>> &edges) {
        for (int i = 0; i < n; ++i)
        {
            p.push_back(i);
        }
        for (auto e : edges)
        {
            if (find(e[0]) == find(e[1]))
                return false;
            p[find(e[0])] = find(e[1]);
            --n;
        }
        return n == 1;
    }

    int find(int x) {
        if (p[x] != x)
        {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};