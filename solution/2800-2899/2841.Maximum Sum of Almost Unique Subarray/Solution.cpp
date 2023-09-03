class Solution {
public:
    long long maxSum(vector<int>& nums, int m, int k) {
        unordered_map<int, int> cnt;
        long long s = 0;
        int n = nums.size();
        for (int i = 0; i < k; ++i) {
            cnt[nums[i]]++;
            s += nums[i];
        }
        long long ans = cnt.size() >= m ? s : 0;
        for (int i = k; i < n; ++i) {
            cnt[nums[i]]++;
            if (--cnt[nums[i - k]] == 0) {
                cnt.erase(nums[i - k]);
            }
            s += nums[i] - nums[i - k];
            if (cnt.size() >= m) {
                ans = max(ans, s);
            }
        }
        return ans;
    }
};