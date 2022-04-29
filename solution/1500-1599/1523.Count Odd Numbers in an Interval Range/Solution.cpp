class Solution {
public:
    int countOdds(int low, int high) {
        return (high + 1 >> 1) - (low >> 1);
    }
};