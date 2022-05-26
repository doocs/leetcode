class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        int n = strs.size();
        int m = strs[0].size();
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                if (strs[j][i] > strs[j + 1][i]) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
};
