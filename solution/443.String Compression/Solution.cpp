class Solution {
public:
    int compress(vector<char>& chars) {
        char pre = chars[0] ;
        int cnt = 1 ;
        int len = 0 ;
        for (int i = 1; ; ++i)
        {
            if (i < chars.size() && chars[i] == pre)
                ++cnt ;
            else
            {
                chars[len++] = pre ;
                
                if (cnt > 1)
                    for (auto c: to_string(cnt))
                        chars[len++] = c ;

                if (i >= chars.size())
                    break ;
                
                pre = chars[i] ;
                cnt = 1 ;
            }
        }
        
        return len ;
    }
};