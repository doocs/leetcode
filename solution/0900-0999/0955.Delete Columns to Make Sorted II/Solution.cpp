class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        int n = strs.size();
        int m = strs[0].size();
        vector<bool> st(n - 1, false);
        int ans = 0;
        for (int j = 0; j < m; ++j) {
            bool must_del = false;
            for (int i = 0; i < n - 1; ++i) {
                if (!st[i] && strs[i][j] > strs[i + 1][j]) {
                    must_del = true;
                    break;
                }
            }
            if (must_del) {
                ++ans;
            } else {
                for (int i = 0; i < n - 1; ++i) {
                    if (!st[i] && strs[i][j] < strs[i + 1][j]) {
                        st[i] = true;
                    }
                }
            }
        }
        return ans;
    }
};
