class Solution {
public:
    int minOperations(int n) {
        int ans = 0, cnt = 0;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ++cnt;
            } else if (cnt > 0) {
                ++ans;
                cnt = cnt == 1 ? 0 : 1;
            }
        }
        ans += cnt == 1 ? 1 : 0;
        ans += cnt > 1 ? 2 : 0;
        return ans;
    }
};