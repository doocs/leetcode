class Solution {
public:
    int minimumOperationsToMakeEqual(int x, int y) {
        unordered_map<int, int> f;
        function<int(int)> dfs = [&](int x) {
            if (y >= x) {
                return y - x;
            }
            if (f.count(x)) {
                return f[x];
            }
            int a = x % 5 + 1 + dfs(x / 5);
            int b = 5 - x % 5 + 1 + dfs(x / 5 + 1);
            int c = x % 11 + 1 + dfs(x / 11);
            int d = 11 - x % 11 + 1 + dfs(x / 11 + 1);
            return f[x] = min({x - y, a, b, c, d});
        };
        return dfs(x);
    }
};