class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        int n = strs[0].size();
        vector<int> f(n, 1);
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (ranges::all_of(strs, [&](const string& s) { return s[j] <= s[i]; })) {
                    f[i] = max(f[i], f[j] + 1);
                }
            }
        }
        return n - ranges::max(f);
    }
};
