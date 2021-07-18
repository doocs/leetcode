class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        int n = triangle.size();
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i + 1; ++j) {
                int mi = INT_MAX;
                if (j > 0) mi = min(mi, triangle[i - 1][j - 1]);
                if (j < i) mi = min(mi, triangle[i - 1][j]);
                triangle[i][j] += mi;
            }
        }
        int res = INT_MAX;
        for (int& val : triangle[n - 1]) {
            res = min(res, val);
        }
        return res;
    }
};