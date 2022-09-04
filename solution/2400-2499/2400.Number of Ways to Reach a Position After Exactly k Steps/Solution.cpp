class Solution {
public:
    unordered_map<int, int> f;
    int mod = 1e9 + 7;
    int j;

    int numberOfWays(int startPos, int endPos, int k) {
        j = endPos;
        return dfs(startPos, k);  
    }

    int dfs(int i, int k) {
        if (f.count(i * 10000 + k)) return f[i * 10000 + k];
        if (abs(i - j) > k) return 0;
        if (k == 0) return i == j;
        int res = dfs(i - 1, k - 1) + dfs(i + 1, k - 1);
        res %= mod;
        f[i * 10000 + k] = res;
        return res;
    }
};