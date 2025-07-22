class Solution {
public:
    vector<int> rowAndMaximumOnes(vector<vector<int>>& mat) {
        vector<int> ans(2);
        for (int i = 0; i < mat.size(); ++i) {
            int cnt = accumulate(mat[i].begin(), mat[i].end(), 0);
            if (ans[1] < cnt) {
                ans = {i, cnt};
            }
        }
        return ans;
    }
};
