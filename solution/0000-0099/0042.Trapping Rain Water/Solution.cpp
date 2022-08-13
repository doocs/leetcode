class Solution {
public:
    int trap(vector<int>& height) {
        int n = height.size();
        if (n < 3) {
            return 0;
        }

        vector<int> lmx(n, height[0]);
        vector<int> rmx(n, height[n - 1]);
        for (int i = 1; i < n; ++i) {
            lmx[i] = max(lmx[i - 1], height[i]);
            rmx[n - 1 - i] = max(rmx[n - i], height[n - 1 - i]);
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += min(lmx[i], rmx[i]) - height[i];
        }
        return res;
    }
};