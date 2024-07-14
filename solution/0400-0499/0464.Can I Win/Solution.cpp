class Solution {
public:
    bool canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        unordered_map<int, int> f;
        function<bool(int, int)> dfs = [&](int mask, int s) {
            if (f.contains(mask)) {
                return f[mask];
            }
            for (int i = 0; i < maxChoosableInteger; ++i) {
                if (mask >> i & 1 ^ 1) {
                    if (s + i + 1 >= desiredTotal || !dfs(mask | 1 << i, s + i + 1)) {
                        return f[mask] = true;
                    }
                }
            }
            return f[mask] = false;
        };
        return dfs(0, 0);
    }
};