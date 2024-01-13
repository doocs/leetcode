class Solution {
public:
    bool nimGame(vector<int>& piles) {
        unordered_map<int, int> memo;
        int p[8] = {1};
        for (int i = 1; i < 8; ++i) {
            p[i] = p[i - 1] * 8;
        }
        auto f = [&](vector<int>& piles) {
            int st = 0;
            for (int i = 0; i < piles.size(); ++i) {
                st += piles[i] * p[i];
            }
            return st;
        };
        function<bool(vector<int>&)> dfs = [&](vector<int>& piles) {
            int st = f(piles);
            if (memo.count(st)) {
                return memo[st];
            }
            for (int i = 0; i < piles.size(); ++i) {
                for (int j = 1; j <= piles[i]; ++j) {
                    piles[i] -= j;
                    if (!dfs(piles)) {
                        piles[i] += j;
                        return memo[st] = true;
                    }
                    piles[i] += j;
                }
            }
            return memo[st] = false;
        };
        return dfs(piles);
    }
};