class Solution {
public:
    long long minimalKSum(vector<int>& nums, int k) {
        nums.push_back(0);
        nums.push_back(2e9);
        sort(nums.begin(), nums.end());
        long long ans = 0;
        for (int i = 0; i < nums.size() - 1 && k > 0; ++i) {
            int m = max(0, min(k, nums[i + 1] - nums[i] - 1));
            ans += 1LL * (nums[i] + 1 + nums[i] + m) * m / 2;
            k -= m;
        }
        return ans;
    }
};