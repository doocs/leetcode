class Solution {
public:
    bool isToeplitzMatrix(vector<vector<int>>& matrix) {
        int n = matrix.size();
        int m = matrix[0].size();
        for(int i = 1;i<n;i++){
        for(int j = 1;j<m;j++){
            if(matrix[i][j] != matrix[i-1][j-1])
                return false;
            
        }
        }
return true;
    }
    
};