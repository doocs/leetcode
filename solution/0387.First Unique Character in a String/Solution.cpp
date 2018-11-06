class Solution {
public:
    int firstUniqChar(string s) {
        vector<int> cnts(26, 0), pos(26, s.size()) ;
        
        
        for (int i = s.size()-1; i >= 0; --i)
        {
            int index = s[i] - 'a' ;
            cnts[index]++ ;
            pos[index] = i ;
        }
        
        int p = s.size() ;
        for (int i = 0; i < 26; ++i)
        {
            if (cnts[i] == 1 && pos[i] < p)
                p = pos[i] ;
        }
        
        return p != s.size()? p: -1 ;
    }
};