class Solution {
private:
    void dfs(vector<vector<int>> &g, 
             vector<vector<int>> &dis, 
             int i, int j, int d)
    {
        if (i >= dis.size() || j >= dis[0].size())
            return ;
        
        d += g[i][j] ;
        if (dis[i][j] >= 0 && d >= dis[i][j])
            return ;
        
        dis[i][j] = d ;
        
        dfs(g, dis, i+1, j, d) ;
        dfs(g, dis, i, j+1, d) ;
    }
public:
    int minPathSum(vector<vector<int>>& grid) {
        vector<vector<int>> dis(grid.size(), vector<int>(grid.at(0).size(), -1)) ;
        dfs(grid, dis, 0, 0, 0) ;
        return dis.back().back() ;
    }
};
