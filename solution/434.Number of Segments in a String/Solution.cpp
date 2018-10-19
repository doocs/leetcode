class Solution {
public:
    int countSegments(string s) {
        if (s.length() < 1)
            return 0 ;
        
        int cnt = isspace(s[0])? 0: 1 ;
        for (int i = 1; i < s.length(); ++i)
            if (!isspace(s[i]) && isspace(s[i-1]))
                ++cnt ;
        return cnt ;
    }
};