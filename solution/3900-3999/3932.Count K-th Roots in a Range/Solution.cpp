class Solution {
public:
    int countKthRoots(int l, int r, int k) {
        if (k == 1) {
            return r - l + 1;
        }
        int ans = 0;
        for (int x = 0;; x++) {
            long long y = 1;
            for (int i = 0; i < k; i++) {
                y *= x;
                if (y > r) {
                    break;
                }
            }
            if (y > r) {
                break;
            }
            if (l <= y && y <= r) {
                ans++;
            }
        }
        return ans;
    }
};