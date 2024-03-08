class Solution {
public:
    int maximumTripletValue(vector<int>& nums) {
        int n = nums.size();
        vector<int> right(n, nums.back());
        for (int i = n - 2; ~i; --i) {
            right[i] = max(nums[i], right[i + 1]);
        }
        set<int> ts;
        ts.insert(nums[0]);
        int ans = 0;
        for (int j = 1; j < n - 1; ++j) {
            if (right[j + 1] > nums[j]) {
                auto it = ts.lower_bound(nums[j]);
                if (it != ts.begin()) {
                    --it;
                    ans = max(ans, *it - nums[j] + right[j + 1]);
                }
            }
            ts.insert(nums[j]);
        }
        return ans;
    }
};