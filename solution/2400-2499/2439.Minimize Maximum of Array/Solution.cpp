class Solution {
public:
    int minimizeArrayValue(vector<int>& nums) {
        int left = 0, right = *max_element(nums.begin(), nums.end());
        auto check = [&](int mx) {
            long d = 0;
            for (int i = nums.size() - 1; i; --i) {
                d = max(0l, d + nums[i] - mx);
            }
            return nums[0] + d <= mx;
        };
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};