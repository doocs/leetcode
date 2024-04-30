class Solution {
public:
    long long maximumSubarraySum(vector<int>& nums, int k) {
        unordered_map<int, long long> p;
        p[nums[0]] = 0;
        long long s = 0;
        const int n = nums.size();
        long long ans = LONG_LONG_MIN;
        for (int i = 0;; ++i) {
            s += nums[i];
            auto it = p.find(nums[i] - k);
            if (it != p.end()) {
                ans = max(ans, s - it->second);
            }
            it = p.find(nums[i] + k);
            if (it != p.end()) {
                ans = max(ans, s - it->second);
            }
            if (i + 1 == n) {
                break;
            }
            it = p.find(nums[i + 1]);
            if (it == p.end() || it->second > s) {
                p[nums[i + 1]] = s;
            }
        }
        return ans == LONG_LONG_MIN ? 0 : ans;
    }
};