class Solution {
private:
    inline string Check(string &s, int widths[])
    {
        widths[1] += widths[0] ;
        widths[2] += widths[1] ;
        widths[3] = s.length() - widths[2] ;
        
        if (widths[3] < 1 || widths[3] > 3 )
            return "" ;
        
        widths[3] = s.length() ;
        
        for (int seg = 0; seg < 4; ++seg)
        {
            int num = 0 ;
            for (int i = (seg == 0? 0: widths[seg-1]); i < widths[seg]; ++i)
            {
                num *= 10 ;
                num += s[i] - '0' ;
            }
            if (num > 255)
                return "" ;
            
            int w = widths[seg] - (seg == 0? 0: widths[seg-1]) ;
            if (w == 3 && num < 100)
                return "" ;
            if (w == 2 && num < 10)
                return "" ;
        }
        string res = s ;
        for (int i = 2; i >= 0; --i)
            res.insert(widths[i], ".") ;
        
        return res ;
    }
public:
    vector<string> restoreIpAddresses(string s) {
        int widths[4] ;
        vector<string> res ;
        for (int i = 0; i < 27; ++i)
        {
            widths[0] = 1 + i%3 ;
            widths[1] = 1 + i/3%3 ;
            widths[2] = 1 + i/9 ;
            
            string tmp = Check(s, widths) ;
            if (tmp.size() != 0)
                res.push_back(tmp) ;
        }
        
        return res; 
    }
};
