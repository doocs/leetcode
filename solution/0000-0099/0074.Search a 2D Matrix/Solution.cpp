class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int m = matrix.size(), n = matrix[0].size();
        int l = 0, h = m * n - 1;
        while (l <= h) {
            int mid = l + ((h - l) >> 1);
            int x = mid / n, y = mid % n;
            if (matrix[x][y] == target) return true;
            if (matrix[x][y] < target) l = mid + 1;
            else h = mid - 1;
        }
        return false;
    }
};