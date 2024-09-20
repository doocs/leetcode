class Solution {
public:
    int atMostNGivenDigitSet(vector<string>& digits, int n) {
        string s = to_string(n);
        unordered_set<int> nums;
        for (auto& x : digits) {
            nums.insert(stoi(x));
        }
        int m = s.size();
        int f[m];
        memset(f, -1, sizeof(f));
        auto dfs = [&](auto&& dfs, int i, bool lead, bool limit) -> int {
            if (i >= m) {
                return lead ? 0 : 1;
            }
            if (!lead && !limit && f[i] != -1) {
                return f[i];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (j == 0 && lead) {
                    ans += dfs(dfs, i + 1, true, limit && j == up);
                } else if (nums.count(j)) {
                    ans += dfs(dfs, i + 1, false, limit && j == up);
                }
            }
            if (!lead && !limit) {
                f[i] = ans;
            }
            return ans;
        };
        return dfs(dfs, 0, true, true);
    }
};