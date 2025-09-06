class Solution {
public:
    bool partitionArray(vector<int>& nums, int k) {
        int n = nums.size();
        if (n % k) {
            return false;
        }
        int m = n / k;
        int mx = *ranges::max_element(nums);
        vector<int> cnt(mx + 1);
        for (int x : nums) {
            if (++cnt[x] > m) {
                return false;
            }
        }
        return true;
    }
};
