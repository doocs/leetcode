class Solution {
public:
    int minAbsoluteDifference(vector<int>& nums, int x) {
        int ans = 1 << 30;
        multiset<int> s;
        for (int i = x; i < nums.size(); ++i) {
            s.insert(nums[i - x]);
            auto it = s.lower_bound(nums[i]);
            if (it != s.end()) {
                ans = min(ans, *it - nums[i]);
            }
            if (it != s.begin()) {
                --it;
                ans = min(ans, nums[i] - *it);
            }
        }
        return ans;
    }
};