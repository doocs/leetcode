class Solution {
public:
    int waysToReachStair(int k) {
        this->k = k;
        return dfs(1, 0, 0);
    }

private:
    unordered_map<long long, int> f;
    int k;

    int dfs(int i, int j, int jump) {
        if (i > k + 1) {
            return 0;
        }
        long long key = ((long long) i << 32) | jump << 1 | j;
        if (f.contains(key)) {
            return f[key];
        }
        int ans = i == k ? 1 : 0;
        if (i > 0 && j == 0) {
            ans += dfs(i - 1, 1, jump);
        }
        ans += dfs(i + (1 << jump), 0, jump + 1);
        f[key] = ans;
        return ans;
    }
};