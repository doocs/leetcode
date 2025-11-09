class Solution {
public:
    int minimumDistance(vector<int>& nums) {
        int n = nums.size();
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < n; ++i) {
            g[nums[i]].push_back(i);
        }
        const int inf = 1 << 30;
        int ans = inf;
        for (auto& [_, ls] : g) {
            int m = ls.size();
            for (int h = 0; h < m - 2; ++h) {
                int i = ls[h];
                int k = ls[h + 2];
                int t = (k - i) * 2;
                ans = min(ans, t);
            }
        }
        return ans == inf ? -1 : ans;
    }
};
