class Solution {
public:
    int maxHeightOfTriangle(int red, int blue) {
        int ans = 0;
        for (int k = 0; k < 2; ++k) {
            int c[2] = {red, blue};
            for (int i = 1, j = k; i <= c[j]; j ^= 1, ++i) {
                c[j] -= i;
                ans = max(ans, i);
            }
        }
        return ans;
    }
};