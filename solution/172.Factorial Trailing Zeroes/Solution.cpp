class Solution {
public:
    int trailingZeroes(int n) {
        int cnt5 = 0 ;
        for (long long i = 5; i <= n; i *= 5)
            cnt5 += n/i ;
        return cnt5 ;
    }
};