class Solution {
public:
    long long maxTotalValue(vector<int>& nums, int k) {
        auto [mn, mx] = minmax_element(nums.begin(), nums.end());
        return 1LL * k * (*mx - *mn);
    }
};
