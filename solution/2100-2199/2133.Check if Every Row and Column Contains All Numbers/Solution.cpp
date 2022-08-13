class Solution {
public:
    bool checkValid(vector<vector<int>>& matrix) {
        int n = matrix.size();
        for (int i = 0; i < n; ++i) {
            vector<bool> seen(n);
            for (int j = 0; j < n; ++j) {
                int v = matrix[i][j] - 1;
                if (seen[v]) return false;
                seen[v] = true;
            }
        }
        for (int j = 0; j < n; ++j) {
            vector<bool> seen(n);
            for (int i = 0; i < n; ++i) {
                int v = matrix[i][j] - 1;
                if (seen[v]) return false;
                seen[v] = true;
            }
        }
        return true;
    }
};