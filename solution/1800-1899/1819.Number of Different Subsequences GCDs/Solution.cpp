class Solution {
public:
    int countDifferentSubsequenceGCDs(vector<int>& nums) {
        int mx = *max_element(nums.begin(), nums.end());
        vector<bool> vis(mx + 1);
        for (int& x : nums) {
            vis[x] = true;
        }
        int ans = 0;
        for (int x = 1; x <= mx; ++x) {
            int g = 0;
            for (int y = x; y <= mx; y += x) {
                if (vis[y]) {
                    g = gcd(g, y);
                    if (g == x) {
                        ++ans;
                        break;
                    }
                }
            }
        }
        return ans;
    }
};