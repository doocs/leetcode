class Solution {
public:
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid) 
    {

        vector<int> h(grid.size(), -1), w(grid[0]) ;
        
        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid[i].size(); ++j)
            {
                if (grid[i][j] > h[i])
                    h[i] = grid[i][j] ;
                if (grid[i][j] > w[j])
                    w[j] = grid[i][j] ;
            }
        int sum = 0 ;
        
        for (int i = 0; i < grid.size(); ++i)
            for (int j = 0; j < grid.size(); ++j)
            {
                int m = h[i] < w[j]? h[i]: w[j] ;
                if (grid[i][j] < m)
                    sum += m - grid[i][j] ;
            }
        
        
        return sum ;
    }
};
