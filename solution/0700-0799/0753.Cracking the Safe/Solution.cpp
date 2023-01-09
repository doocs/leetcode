class Solution {
public:
    string crackSafe(int n, int k) {
        unordered_set<int> vis;
        int mod = pow(10, n - 1);
        string ans;
        function<void(int)> dfs = [&](int u) {
            for (int x = 0; x < k; ++x) {
                int e = u * 10 + x;
                if (!vis.count(e)) {
                    vis.insert(e);
                    dfs(e % mod);
                    ans += (x + '0');
                }
            }
        };
        dfs(0);
        ans += string(n - 1, '0');
        return ans;
    }
};