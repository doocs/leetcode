class Solution {
public:
    bool findNumberIn2DArray(vector<vector<int>>& matrix, int target) {
        for (auto& row : matrix) {
            int j = lower_bound(row.begin(), row.end(), target) - row.begin();
            if (j < matrix[0].size() && row[j] == target) {
                return true;
            }
        }
        return false;
    }
};