class Solution {
public:
    int makeConnected(int n, vector<vector<int>>& connections) {
        vector<int> p(n);
        iota(p.begin(), p.end(), 0);
        int cnt = 0;
        function<int(int)> find = [&](int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        for (const auto& c : connections) {
            int pa = find(c[0]), pb = find(c[1]);
            if (pa == pb) {
                ++cnt;
            } else {
                p[pa] = pb;
                --n;
            }
        }
        return cnt >= n - 1 ? n - 1 : -1;
    }
};
