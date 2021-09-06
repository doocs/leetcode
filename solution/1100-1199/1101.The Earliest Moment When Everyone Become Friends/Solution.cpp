class Solution {
public:
    vector<int> p;

    int earliestAcq(vector<vector<int>>& logs, int n) {
        for (int i = 0; i < n; ++i)
            p.push_back(i);
        sort(logs.begin(), logs.end());
        for (auto log : logs)
        {
            int a = log[1], b = log[2];
            int pa = find(a), pb = find(b);
            if (pa == pb)
                continue;
            p[pa] = pb;
            --n;
            if (n == 1)
                return log[0];
        }
        return -1;
    }

    int find(int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }
};