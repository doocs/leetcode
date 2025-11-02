class Solution {
public:
    long long maxProduct(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        long long a = nums[0], b = nums[1];
        long long c = nums[n - 2], d = nums[n - 1];
        const int x = 100000;
        return max({a * b * x, c * d * x, -a * d * x});
    }
};
