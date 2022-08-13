using ll = long long;

class Solution {
public:
    int n;
    unordered_map<ll, bool> memo;

    bool canWin(string currentState) {
        n = currentState.size();
        ll mask = 0;
        for (int i = 0; i < n; ++i)
            if (currentState[i] == '+') mask |= 1ll << i;
        return dfs(mask);
    }

    bool dfs(ll mask) {
        if (memo.count(mask)) return memo[mask];
        for (int i = 0; i < n - 1; ++i) {
            if ((mask & (1ll << i)) == 0 || (mask & (1ll << (i + 1))) == 0) continue;
            if (dfs(mask ^ (1ll << i) ^ (1ll << (i + 1)))) continue;
            memo[mask] = true;
            return true;
        }
        memo[mask] = false;
        return false;
    }
};