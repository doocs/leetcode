class Solution {
public:
    vector<int> p;

    int makeConnected(int n, vector<vector<int>>& connections) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        int cnt = 0;
        for (auto& e : connections) {
            int a = e[0], b = e[1];
            if (find(a) == find(b))
                ++cnt;
            else {
                p[find(a)] = find(b);
                --n;
            }
        }
        return n - 1 > cnt ? -1 : n - 1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};