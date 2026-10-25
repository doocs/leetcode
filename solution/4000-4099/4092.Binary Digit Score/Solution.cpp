class Solution {
public:
    int calculateScore(int a, int b) {
        int s1 = __builtin_popcount(a);
        int s2 = __builtin_popcount(b);
        return s1 >= s2 ? s1 * s2 : s2 / s1;
    }
};