class Solution {
public:
    vector<int> numsSameConsecDiff(int n, int k) {
        vector<int> ans;
        int boundary = pow(10, n - 1);
        auto dfs = [&](auto&& dfs, int x) {
            if (x >= boundary) {
                ans.push_back(x);
                return;
            }
            int last = x % 10;
            if (last + k < 10) {
                dfs(dfs, x * 10 + last + k);
            }
            if (k != 0 && last - k >= 0) {
                dfs(dfs, x * 10 + last - k);
            }
        };
        for (int i = 1; i < 10; ++i) {
            dfs(dfs, i);
        }
        return ans;
    }
};
