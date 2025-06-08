class Solution {
public:
    vector<vector<int>> shiftGrid(vector<vector<int>>& grid, int k) {
        int n = grid.size();
        int m = grid[0].size();
        int total = n * m;
        k = k%total;
        vector<vector<int>>res(n,vector<int>(m));
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                int p = (i*m+j+k)%total;
                int newRow = p/m;
                int newCol = p%m;
                res[newRow][newCol] = grid[i][j];
            }
        }
        return res;
    }
};