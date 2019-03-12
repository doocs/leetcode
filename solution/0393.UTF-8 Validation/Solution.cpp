class Solution {
public:
    bool validUtf8(vector<int>& data) {
        const unsigned modeContinue = 0xc0 ;
        
        int conti = 0 ;
        for (auto it = data.begin(); it < data.end(); ++it)
        {
            
            if (0 == conti) // 首字节
            {
                if (*it < 0x80)
                    continue ;
                else if (*it < 0xe0)
                    conti = 1 ;
                else if (*it < 0xf0)
                    conti = 2 ;
                else if (*it < 0xf8)
                    conti = 3 ;
                else
                    return false ;
            }
            else // 后续字节
            {
                --conti ;
                if ((*it & modeContinue) != 0x80)
                    return false ;
            }
        }
        
        return 0 == conti ;
    }
};