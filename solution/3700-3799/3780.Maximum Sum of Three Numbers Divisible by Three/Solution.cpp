class Solution {
public:
    int maximumSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> g(3);
        for (int x : nums) {
            g[x % 3].push_back(x);
        }
        int ans = 0;
        for (int a = 0; a < 3; a++) {
            if (!g[a].empty()) {
                int x = g[a].back();
                g[a].pop_back();
                for (int b = 0; b < 3; b++) {
                    if (!g[b].empty()) {
                        int y = g[b].back();
                        g[b].pop_back();
                        int c = (3 - (a + b) % 3) % 3;
                        if (!g[c].empty()) {
                            int z = g[c].back();
                            ans = max(ans, x + y + z);
                        }
                        g[b].push_back(y);
                    }
                }
                g[a].push_back(x);
            }
        }
        return ans;
    }
};
