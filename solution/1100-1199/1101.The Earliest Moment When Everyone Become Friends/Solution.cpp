class Solution {
public:
    vector<int> p;

    int earliestAcq(vector<vector<int>>& logs, int n) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        sort(logs.begin(), logs.end());
        for (auto& log : logs) {
            int t = log[0], a = log[1], b = log[2];
            if (find(a) == find(b)) continue;
            p[find(a)] = find(b);
            if (--n == 1) return t;
        }
        return -1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};