class Solution {
public:
    int fib(int N) {
        if (N < 2)
            return N > 0? 1: 0 ;
        int aN_2 = 0, aN_1 = 1, aN ;
        while (--N)
        {
            aN = aN_2 + aN_1 ;
            aN_2 = aN_1 ;
            aN_1 = aN ;
        }
        return aN ;
    }
};