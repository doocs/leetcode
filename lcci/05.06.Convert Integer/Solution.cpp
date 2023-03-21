class Solution {
public:
    int convertInteger(int A, int B) {
        unsigned int c = A ^ B;
        return __builtin_popcount(c);
    }
};