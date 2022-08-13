class Solution {
public:
    vector<int> getMaxMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> s(m + 1, vector<int>(n));
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                s[i + 1][j] = s[i][j] + matrix[i][j];
        int mx = matrix[0][0];
        vector<int> ans(4);
        for (int i1 = 0; i1 < m; ++i1) {
            for (int i2 = i1; i2 < m; ++i2) {
                vector<int> nums;
                for (int j = 0; j < n; ++j)
                    nums.push_back(s[i2 + 1][j] - s[i1][j]);
                int start = 0;
                int f = nums[0];
                for (int j = 1; j < n; ++j) {
                    if (f > 0)
                        f += nums[j];
                    else {
                        f = nums[j];
                        start = j;
                    }
                    if (f > mx) {
                        mx = f;
                        ans[0] = i1;
                        ans[1] = start;
                        ans[2] = i2;
                        ans[3] = j;
                    }
                }
            }
        }
        return ans;
    }
};