class Solution {
public:
    int maximumSum(vector<int>& nums) {
        vector<vector<int>> d(100);
        for (int& v : nums) {
            int y = 0;
            for (int x = v; x > 0; x /= 10) {
                y += x % 10;
            }
            d[y].emplace_back(v);
        }
        int ans = -1;
        for (auto& vs : d) {
            if (vs.size() > 1) {
                sort(vs.rbegin(), vs.rend());
                ans = max(ans, vs[0] + vs[1]);
            }
        }
        return ans;
    }
};