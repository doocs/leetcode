class Solution {
public:
    int totalNQueens(int n) {
        bitset<10> cols;
        bitset<20> dg;
        bitset<20> udg;
        int ans = 0;
        function<void(int)> dfs = [&](int i) {
            if (i == n) {
                ++ans;
                return;
            }
            for (int j = 0; j < n; ++j) {
                int a = i + j, b = i - j + n;
                if (cols[j] || dg[a] || udg[b]) continue;
                cols[j] = dg[a] = udg[b] = 1;
                dfs(i + 1);
                cols[j] = dg[a] = udg[b] = 0;
            }
        };
        dfs(0);
        return ans;
    }
};