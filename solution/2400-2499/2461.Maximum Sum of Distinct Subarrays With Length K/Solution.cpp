class Solution {
public:
    long long maximumSubarraySum(vector<int>& nums, int k) {
        int n = nums.size();
        unordered_map<int, int> cnt;
        long long s = 0, ans = 0;
        for (int i = 0; i < k; ++i) {
            cnt[nums[i]]++;
            s += nums[i];
        }
        for (int i = k; i < n; ++i) {
            if (cnt.size() == k) ans = max(ans, s);
            cnt[nums[i]]++;
            cnt[nums[i - k]]--;
            if (cnt[nums[i - k]] == 0) cnt.erase(nums[i - k]);
            s += nums[i];
            s -= nums[i - k];
        }
        if (cnt.size() == k) ans = max(ans, s);
        return ans;
    }
};