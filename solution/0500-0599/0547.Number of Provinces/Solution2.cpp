class Solution {
public:
    int findCircleNum(vector<vector<int>>& isConnected) {
        int n = isConnected.size();
        int p[n];
        iota(p, p + n, 0);
        auto find = [&](this auto&& find, int x) -> int {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        };
        int ans = n;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (isConnected[i][j]) {
                    int pa = find(i), pb = find(j);
                    if (pa != pb) {
                        p[pa] = pb;
                        --ans;
                    }
                }
            }
        }
        return ans;
    }
};
