class Solution {
public:
    bool winnerSquareGame(int n) {
        vector<int> f(n + 1, -1);

        auto dfs = [&](this auto&& dfs, int i) -> bool {
            if (i <= 0) {
                return false;
            }
            if (f[i] != -1) {
                return f[i];
            }

            int k = sqrt(i);
            for (int j = 1; j <= k; j++) {
                if (!dfs(i - j * j)) {
                    return f[i] = true;
                }
            }

            return f[i] = false;
        };

        return dfs(n);
    }
};