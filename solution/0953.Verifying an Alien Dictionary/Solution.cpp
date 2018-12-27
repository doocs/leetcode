class Solution {
private:
    int ala[26] = {0, } ;
public:
    bool isAlienSorted(vector<string>& words, string order) 
    {
        if (words.size() <= 1)
            return true ;
        
        int i = 0 ;
        for (i = 0; i < order.size(); ++i)
            ala[order[i] - 'a'] = i ;
        
        for (i = 1; i < words.size(); ++i)
            if (!cmp(words[i-1], words[i]))
                return false ;
        
        return true ;
    }
    
    bool cmp(string a, string b) 
    {
        int i, len = min(a.size(), b.size()) ;
        for (i = 0; i < len; ++i)
        {
            int c = ala[ a[i] - 'a' ] - ala[ b[i] - 'a' ] ;
            if (c < 0)
                return true ;
            else if (c > 0)
                return false ;
        }
        
        return i == a.size() ;
    }
};