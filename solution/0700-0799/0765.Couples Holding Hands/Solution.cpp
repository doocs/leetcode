class Solution {
public:
    vector<int> p;

    int minSwapsCouples(vector<int>& row) {
        int n = row.size() >> 1;
        p.resize(n);
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int i = 0; i < row.size(); i += 2) {
            int a = row[i] >> 1, b = row[i + 1] >> 1;
            p[find(a)] = find(b);
        }
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (i == find(i))
                ++cnt;
        }
        return n - cnt;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
};