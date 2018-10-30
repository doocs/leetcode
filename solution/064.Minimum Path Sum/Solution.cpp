class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        int row = grid.size();
        if(row == 0)return 0;
        int column = grid[0].size();
        
        vector<vector<int>> path(row,vector<int>(column,0));
        path[0][0] = grid[0][0];
        for(int i = 1 ; i < column ; i++)path[0][i] = path[0][i-1] + grid[0][i];
        for(int i = 1 ; i < row;i++)path[i][0] = path[i-1][0]+grid[i][0];
        
        for(int i = 1;i<row;i++){
            for(int j = 1;j<column;j++){
                path[i][j] = min(path[i-1][j],path[i][j-1]) + grid[i][j];
            }
        }
        
        return path[row-1][column-1];
    }
};