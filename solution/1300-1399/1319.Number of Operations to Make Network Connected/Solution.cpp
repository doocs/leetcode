class Solution {
public:
    vector<int> p;

    int makeConnected(int n, vector<vector<int>> &connections) {
        p.resize(n);
        for (int i = 0; i < n; ++i)
        {
            p[i] = i;
        }
        int cnt = 0;
        for (auto e : connections)
        {
            if (find(e[0]) == find(e[1]))
            {
                ++cnt;
            }
            else
            {
                p[find(e[0])] = find(e[1]);
            }
        }
        int total = 0;
        for (int i = 0; i < n; ++i)
        {
            if (i == find(i))
            {
                ++total;
            }
        }
        return total - 1 > cnt ? -1 : total - 1;
    }

    int find(int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }
};