class Solution {
public:
    int maximumSum(vector<int>& nums) {
        int d[100]{};
        int ans = -1;
        for (int v : nums) {
            int x = 0;
            for (int y = v; y; y /= 10) {
                x += y % 10;
            }
            if (d[x]) {
                ans = max(ans, d[x] + v);
            }
            d[x] = max(d[x], v);
        }
        return ans;
    }
};