class Solution {
public:
    int triangleNumber(vector<int>& nums) {
        ranges::sort(nums);
        int ans = 0, n = nums.size();
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int sum = nums[i] + nums[j];
                auto it = ranges::lower_bound(nums.begin() + j + 1, nums.end(), sum);
                int k = int(it - nums.begin()) - 1;
                ans += max(0, k - j);
            }
        }
        return ans;
    }
};
