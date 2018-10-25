class Solution {
public:
    uint32_t reverseBits(uint32_t n) {
        uint32_t res = 0 ;
        uint32_t tmp = 1 << 31 ;
        while (n)
        {
            if (n&1)
                res |= tmp ;
            tmp >>= 1 ;
            n >>= 1 ;
        }
        
        return res ;
    }
};