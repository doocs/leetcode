class Solution {
public:
    bool v[55][55] = {0, } ; // ∑√Œ ±Íº«
    int maxAreaOfIsland(vector<vector<int>>& grid) 
	{
        int maxAera = 0 ;
        for (int i = 0; i < grid.size(); ++i)
        {
            if (0 == grid.size())
            {
                return 0 ;
            }
            
            for (int j = 0; j < grid[0].size(); ++j)
            {
                if (1 == grid[i][j] && !v[i][j])
                {
                    int cnt = calcAera(grid, i, j) ;
                    if (cnt > maxAera)
                        maxAera = cnt ;
                }
            }
        }
        return maxAera ;
    }
    
    int calcAera(vector<vector<int>>& grid, int i, int j)
    {
        if (i < 0 || i >= grid.size())
            return 0 ;
        if (j < 0 || j >= grid[0].size())
            return 0 ;
        
        if (v[i][j])
            return 0 ;
        
        v[i][j] = true ;
        
        
        if (grid[i][j])
        {
            return 1 
                + calcAera(grid, i-1, j)
                + calcAera(grid, i+1, j)
                + calcAera(grid, i, j-1)
                + calcAera(grid, i, j+1) ;
        }
        else
            return 0 ;
    }
} ;