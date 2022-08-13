class Solution {
public:
    int maximumProduct(vector<int>& nums, int k) {
        int mod = 1e9 + 7;
        make_heap(nums.begin(), nums.end(), greater<int>());
        while (k--) {
            pop_heap(nums.begin(), nums.end(), greater<int>());
            ++nums.back();
            push_heap(nums.begin(), nums.end(), greater<int>());
        }
        long long ans = 1;
        for (int v : nums) ans = (ans * v) % mod;
        return ans;
    }
};