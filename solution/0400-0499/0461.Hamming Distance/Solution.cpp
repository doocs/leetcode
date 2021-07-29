class Solution {
public:
    int hammingDistance(int x, int y) {
        x ^= y;
        int count = 0;
        while (x) {
            ++count;
            x &= (x - 1);
        }
        return count;
    }
};