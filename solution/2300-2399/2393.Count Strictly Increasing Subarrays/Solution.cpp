class Solution {
public:
    long long countSubarrays(vector<int>& nums) {
        long long ans = 0;
        int i = 0, n = nums.size();
        while (i < n) {
            int j = i + 1;
            while (j < n && nums[j] > nums[j - 1]) {
                ++j;
            }
            int cnt = j - i;
            ans += 1ll * (1 + cnt) * cnt / 2;
            i = j;
        }
        return ans;
    }
};