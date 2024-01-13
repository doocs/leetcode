class Solution {
public:
    int maximum(int a, int b) {
        int k = ((static_cast<long long>(a) - static_cast<long long>(b)) >> 63) & 1;
        return a * (k ^ 1) + b * k;
    }
};