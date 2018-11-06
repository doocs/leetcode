class Solution {
public:
    int maxProduct(vector<string>& words) {
        vector<int> v(words.size(), 0) ;
        for (int i = 0; i < words.size(); ++i)
            for (auto ch: words[i])
                v[i] |= 1 << (ch-'a') ;
        
        int M = 0 ;
        for (int i = 0; i < words.size(); ++i)
            for (int j = i+1; j < words.size(); ++j)
                if ((v[i] & v[j]) == 0)
                    M = max(M, (int)words[i].size() * (int)words[j].size()) ;
        return M ;
    }
};