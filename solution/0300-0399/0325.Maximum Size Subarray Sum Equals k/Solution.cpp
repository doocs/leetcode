class Solution {
public:
    int maxSubArrayLen(vector<int>& nums, int k) {
        unordered_map<long long, int> d{{0, -1}};
        int ans = 0;
        long long s = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i];
            if (d.count(s - k)) {
                ans = max(ans, i - d[s - k]);
            }
            if (!d.count(s)) {
                d[s] = i;
            }
        }
        return ans;
    }
};