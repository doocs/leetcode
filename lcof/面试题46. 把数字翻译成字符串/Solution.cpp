class Solution {
public:
    int translateNum(int num) {
        string s = to_string(num);
        int n = s.size();
        int f[12]{};
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n - 1) {
                return 1;
            }
            if (f[i]) {
                return f[i];
            }
            int ans = dfs(i + 1);
            if (s[i] == '1' || (s[i] == '2' && s[i + 1] < '6')) {
                ans += dfs(i + 2);
            }
            return f[i] = ans;
        };
        return dfs(0);
    }
};