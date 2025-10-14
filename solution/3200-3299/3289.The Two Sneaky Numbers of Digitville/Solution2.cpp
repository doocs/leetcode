class Solution {
public:
    vector<int> getSneakyNumbers(vector<int>& nums) {
        int n = nums.size() - 2;
        int xx = nums[n] ^ nums[n + 1];
        for (int i = 0; i < n; ++i) {
            xx ^= i ^ nums[i];
        }
        int k = __builtin_ctz(xx);
        vector<int> ans(2);
        for (int x : nums) {
            ans[(x >> k) & 1] ^= x;
        }
        for (int i = 0; i < n; ++i) {
            ans[(i >> k) & 1] ^= i;
        }
        return ans;
    }
};
