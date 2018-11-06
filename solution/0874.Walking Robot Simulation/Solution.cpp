class Solution {
public:
    int robotSim(vector<int>& commands, vector<vector<int>>& obstacles) {
        set<pair<int, int>> s ;
        for (auto o: obstacles)
            s.insert(pair<int, int>(o[0], o[1])) ;
        
        int dir = 0 ;
        int x = 0, y = 0 ;
        int m = 0 ;
        
        for (auto c: commands)
        {
            //cout << c << ":" << x << ' ' << y << endl ;
            long long d = x*x + y*y ;
            if (m < d)
                m = d ;
            if (-2 == c)
            {
                dir += 3 ;
            }
            else if (-1 == c)
            {
                ++dir ;
            }
            else
            {
                int step = c ;
                dir %= 4 ;
                if (0 == dir)
                {
                    while (step--)
                    {
                        if (s.find(pair<int, int>(x, y+1)) != s.end())
                            break ;
                        //cout << "++i" << endl ;
                        ++y ;
                    }
                     
                }
                else if (1 == dir)
                {
                    while (step--)
                    {
                        if (s.find(pair<int, int>(x+1, y)) != s.end())
                            break ;
                        ++x ;
                    }
                }
                else if (2 == dir)
                {
                    while (step--)
                    {
                        if (s.find(pair<int, int>(x, y-1)) != s.end())
                            break ;
                        --y ;
                    }
                }
                else if (3 == dir)
                {
                    while (step--)
                    {
                        if (s.find(pair<int, int>(x-1, y)) != s.end())
                            break ;
                        --x ;
                    }
                }
            }
        }
        
        //cout  << ":" << x << ' ' << y << endl ;
        
        return max(m, x*x + y*y);
    }
};