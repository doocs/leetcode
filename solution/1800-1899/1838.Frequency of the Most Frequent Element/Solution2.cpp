class Solution {
public:
    int maxFrequency(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = 1;
        long long s = 0;
        for (int i = 1, j = 0; i < nums.size(); ++i) {
            s += 1LL * (nums[i] - nums[i - 1]) * (i - j);
            while (s > k) {
                s -= nums[i] - nums[j++];
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};