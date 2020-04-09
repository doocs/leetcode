static const auto io_speed_up = []()
{
	std::ios::sync_with_stdio(false) ;
	cin.tie(nullptr) ;
	return 0 ;
}() ;

class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        int l = s.size() - p.size() ;
        if (l < 0)
            return {} ;
        vector<int> pt(128, 0) ;
        vector<int> st(128, 0) ;
        vector<int> res ;
        
        for (auto ch: p)
            ++pt[ch] ;
                
        for (int i = 0; i <= l; ++i)
        {
            for (int j = 0; j <= p.size(); ++j)
            {
                if (p.size() == j)
                {
                    if (match(st, pt))
                    {
                        res.push_back(i) ;
                    }
                    --st[s[i]] ;
                    ++i ;
                    j-=2 ;
                    continue ;
                }
                
                char ch = s[i+j] ;
                if (0 == pt[ch])
                {
                    i = i+j ;
                    clear(st) ;
                    break ;
                }

                if (pt[ch] < ++st[ch])
                {
                    clear(st) ;
                    break ;
                }
            }
        }
        
        return res ;
    }
    
    inline void clear(vector<int> &v)
    {
        for (int i = 'a'; i <= 'z'; ++i)
            v[i] = 0 ;
    }
    
    inline bool match(vector<int> &a, vector<int> &b)
    {
        for (int i = 'a'; i <= 'z'; ++i)
            if (a[i] != b[i])
                return false ;
        return true ;
    }
    
    
};