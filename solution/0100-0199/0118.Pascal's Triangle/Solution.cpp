class Solution {
public:
    vector<vector<int>> generate(int numRows) {
        vector<vector<int>> res;
        for (int i = 0; i < numRows; ++i) {
            vector<int> t(i + 1);
            t[0] = 1;
            t[i] = 1;
            for (int j = 1; j < i; ++j) {
                t[j] = res[i - 1][j - 1] + res[i - 1][j];
            }
            res.push_back(t);
        }
        return res;
    }
};