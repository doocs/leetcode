class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        vector<int> ans;
        if (matrix.size() == 0)
            return ans;
        int left = 0, top = 0, bottom = matrix.size() - 1, right = matrix[0].size() - 1;
        while (true) {
            for (int i = left; i <= right; i++)
                ans.push_back(matrix[top][i]);
            top++;
            if (top > bottom)
                break;
            for (int i = top; i <= bottom; i++)
                ans.push_back(matrix[i][right]);
            right--;
            if (right < left)
                break;
            for (int i = right; i >= left; i--)
                ans.push_back(matrix[bottom][i]);
            bottom--;
            if (bottom < top)
                break;
            for (int i = bottom; i >= top; i--)
                ans.push_back(matrix[i][left]);
            left++;
            if (left > right)
                break;
        }
        return ans;
    }
};