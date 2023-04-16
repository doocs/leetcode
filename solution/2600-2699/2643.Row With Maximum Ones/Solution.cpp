class Solution {
public:
    vector<int> rowAndMaximumOnes(vector<vector<int>>& mat) {
        vector<int> ans(2);
        for (int i = 0; i < mat.size(); ++i) {
            int cnt = 0;
            for (auto& x : mat[i]) {
                cnt += x == 1;
            }
            if (ans[1] < cnt) {
                ans[0] = i;
                ans[1] = cnt;
            }
        }
        return ans;
    }
};