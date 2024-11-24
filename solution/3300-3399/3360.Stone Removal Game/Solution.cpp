class Solution {
public:
    bool canAliceWin(int n) {
        int x = 10, k = 0;
        while (n >= x) {
            n -= x;
            --x;
            ++k;
        }
        return k % 2;
    }
};
