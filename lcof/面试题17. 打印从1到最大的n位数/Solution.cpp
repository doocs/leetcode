class Solution {
public:
    vector<int> printNumbers(int n) {
        vector<int> ans(pow(10, n) - 1);
        iota(ans.begin(), ans.end(), 1);
        return ans;
    }

    vector<string> print(int n) {
        vector<string> ans;
        string s;
        function<void(int, int)> dfs = [&](int i, int j) {
            if (i == j) {
                ans.push_back(s);
                return;
            }
            int k = i ? 0 : 1;
            for (; k < 10; ++k) {
                s.push_back(k + '0');
                dfs(i + 1, j);
                s.pop_back();
            }
        };
        for (int i = 1; i <= n; ++i) {
            dfs(0, i);
        }
        return ans;
    }
};