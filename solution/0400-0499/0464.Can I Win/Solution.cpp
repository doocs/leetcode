class Solution {
public:
    bool canIWin(int maxChoosableInteger, int desiredTotal) {
        int s = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (s < desiredTotal) return false;
        unordered_map<int, bool> memo;
        return dfs(0, 0, maxChoosableInteger, desiredTotal, memo);
    }

    bool dfs(int state, int t, int maxChoosableInteger, int desiredTotal, unordered_map<int, bool>& memo) {
        if (memo.count(state)) return memo[state];
        bool res = false;
        for (int i = 1; i <= maxChoosableInteger; ++i) {
            if ((state >> i) & 1) continue;
            if (t + i >= desiredTotal || !dfs(state | 1 << i, t + i, maxChoosableInteger, desiredTotal, memo)) {
                res = true;
                break;
            }
        }
        memo[state] = res;
        return res;
    }
};