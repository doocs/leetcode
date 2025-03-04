class Solution {
public:
    int largestInteger(vector<int>& nums, int k) {
        if (k == 1) {
            unordered_map<int, int> cnt;
            for (int x : nums) {
                ++cnt[x];
            }
            int ans = -1;
            for (auto& [x, v] : cnt) {
                if (v == 1) {
                    ans = max(ans, x);
                }
            }
            return ans;
        }
        int n = nums.size();
        if (k == n) {
            return ranges::max(nums);
        }
        auto f = [&](int k) -> int {
            for (int i = 0; i < n; ++i) {
                if (i != k && nums[i] == nums[k]) {
                    return -1;
                }
            }
            return nums[k];
        };
        return max(f(0), f(n - 1));
    }
};
