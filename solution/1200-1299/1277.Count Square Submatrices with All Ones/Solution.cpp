class Solution {
public:
    int countSquares(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> dp = matrix;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] && i > 0 && j > 0) {
                    int edge = min(dp[i][j-1], dp[i-1][j]);
                    if (edge > 0) {
                        if (matrix[i-edge][j-edge]) {
                            dp[i][j] = edge + 1;
                        } else {
                            dp[i][j] = edge;
                        }
                    } 
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans += dp[i][j];
            }
        }
        return ans;
    }
};