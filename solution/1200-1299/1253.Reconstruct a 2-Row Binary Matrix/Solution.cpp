class Solution {
public:
    vector<vector<int>> reconstructMatrix(int upper, int lower, vector<int>& colsum) {
        int n = colsum.size();
        vector<vector<int>> ans(2, vector<int>(n));
        for (int j = 0; j < n; ++j) {
            if (colsum[j] == 2) {
                ans[0][j] = ans[1][j] = 1;
                upper--;
                lower--;
            }
            if (colsum[j] == 1) {
                if (upper > lower) {
                    upper--;
                    ans[0][j] = 1;
                } else {
                    lower--;
                    ans[1][j] = 1;
                }
            }
            if (upper < 0 || lower < 0) {
                break;
            }
        }
        return upper || lower ? vector<vector<int>>() : ans;
    }
};