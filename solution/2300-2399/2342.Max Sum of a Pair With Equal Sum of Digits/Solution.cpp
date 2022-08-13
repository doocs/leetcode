class Solution {
public:
    int maximumSum(vector<int>& nums) {
        unordered_map<int, vector<int>> d;
        for (int i = 0; i < nums.size(); ++i) {
            int v = nums[i];
            int t = 0;
            while (v) {
                t += v % 10;
                v /= 10;
            }
            d[t].push_back(nums[i]);
        }
        int ans = -1;
        for (auto& [_, v] : d) {
            int n = v.size();
            if (n > 1) {
                sort(v.begin(), v.end());
                ans = max(ans, v[n - 1] + v[n - 2]);
            }
        }
        return ans;
    }
};