class Solution {
public:
    int findComplement(int num) {
        return num ^ ((1LL << (64 - __builtin_clzll(num))) - 1);
    }
};