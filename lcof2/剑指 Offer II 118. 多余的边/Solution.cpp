class Solution {
public:
    vector<int> p;

    vector<int> findRedundantConnection(vector<vector<int>> &edges) {
        p.resize(1010);
        for (int i = 0; i < p.size(); ++i)
        {
            p[i] = i;
        }
        for (auto e : edges)
        {
            if (find(e[0]) == find(e[1]))
            {
                return e;
            }
            p[find(e[0])] = find(e[1]);
        }
        return {};
    }

    int find(int x) {
        if (p[x] != x)
        {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};