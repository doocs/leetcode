class Solution {
public:
    bool searchMatrix(vector<vector<int>>& matrix, int target) {
        if(matrix.empty())return false;
        
        size_t row = matrix.size();
        size_t column = matrix[0].size();
        if(column == 0 || column == 0)return false;
        
        if(target < matrix[0][0] || target > matrix[row-1][column-1])return false;
        
        for(int i = 0;i<row;i++){
            if(matrix[i][column-1]<target)continue;

            auto iter = find(matrix[i].begin(),matrix[i].end(),target);
            if(iter != matrix[i].end())return true;
            else return false;
        }
        
        return false;
    } 
};