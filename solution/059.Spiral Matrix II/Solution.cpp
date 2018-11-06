class Solution {
public:
    vector<vector<int>> generateMatrix(int n) {
        if (0 == n)
            return vector<vector<int>>() ;
        vector<vector<int>> res(n, vector<int>(n, 0)) ;
        
        int i = 0, j = 0 ;
        
        int dir = 0 ;
        
        int n2 = n*n ;
        
        for (int d = 1; d <= n2; ++d)
        {
            res[i][j] = d ;
            //cout << d << endl ;
            switch (dir)
            {
                case 0:
                    ++j ;
                    if (j >= n || res[i][j])
                    {
                        dir++ ;
                        --j ;
                        ++i ;
                    }
                    break ;
                case 1:
                    ++i ;
                    if (i >= n || res[i][j])
                    {
                        dir++ ;
                        --i ;
                        --j ;
                    }
                    break ;
                case 2:
                    --j ;
                    if (j < 0 || res[i][j])
                    {
                        dir++ ;
                        ++j ;
                        --i ;
                    }
                    break ;
                case 3:
                    --i ;
                    if (i < 0 || res[i][j])
                    {
                        dir = 0 ;
                        ++i ;
                        ++j ;
                    }
                    
                    break ;
                default:
                    break ;
            }
            
        }
        
        return res ;
    }
};