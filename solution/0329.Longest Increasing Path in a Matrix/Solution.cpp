class Solution {
private: 
    int Dfs(vector<vector<int>> &m, vector<vector<int>> &v, 
             int i, int j, 
             bool first, int lastNum)
    {
        if (i < 0 || i >= m.size()
           || j < 0 || j >= m[0].size())
            return 0 ;
        
        if (!first && lastNum >= m[i][j])
            return 0 ;
        
        if (v[i][j] >= 0)
            return v[i][j] ;
        
        int d1 = Dfs(m, v, i-1, j, false, m[i][j]) ;
        int d2 = Dfs(m, v, i+1, j, false, m[i][j]) ;
        int d3 = Dfs(m, v, i, j-1, false, m[i][j]) ;
        int d4 = Dfs(m, v, i, j+1, false, m[i][j]) ;
        v[i][j] = 1 + max(max(d1, d2), max(d3, d4)) ;
        
        return v[i][j] ;
    }
public:
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        if (matrix.size() == 0)
            return 0 ;
        const int h = matrix.size(), w = matrix[0].size() ;
        vector<vector<int>> visited(h, vector<int>(w, -1)) ;
        
        int M = 0 ;
        for (int i = 0; i < h; ++i)
            for (int j = 0; j < w; ++j)
            {
                if (j + 1 < w && matrix[i][j] > matrix[i][j+1]
                   || i + 1 < h && matrix[i][j] > matrix[i+1][j])
                {
                    continue ;
                }
                else
                {
                    M = max(M, Dfs(matrix, visited, i, j, true, 0)) ;
                }
            }
        return M ;
    }
};

static const auto __ = []() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);
	return nullptr;
}();