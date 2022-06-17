class Solution {
public:
    vector<long long> sumOfThree(long long num) {
        if (num % 3) return {};
        long long x = num / 3;
        return {x - 1, x, x + 1};
    }
};