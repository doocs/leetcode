class Solution {
public:
    int fib(int N) {
        int a[2] = {0, 1} ;
        for (int i = 2; i <= N; ++i)
            a[i&1] += a[i&1^1] ;
        return a[N&1] ;
    }
};