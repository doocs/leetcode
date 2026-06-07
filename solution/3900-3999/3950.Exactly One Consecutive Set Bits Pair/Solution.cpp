class Solution {
public:
    bool consecutiveSetBits(int n) {
        bool vis = false;
        for (int pre = 0; n > 0; n >>= 1) {
            int cur = n & 1;
            if (pre == cur && cur == 1) {
                if (vis) {
                    return false;
                }
                vis = true;
            }
            pre = cur;
        }
        return vis;
    }
};